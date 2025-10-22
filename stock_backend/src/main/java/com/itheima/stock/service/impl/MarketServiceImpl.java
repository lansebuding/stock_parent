package com.itheima.stock.service.impl;

import cn.hutool.http.server.HttpServerResponse;
import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.stock.mapper.StockBlockRtInfoMapper;
import com.itheima.stock.mapper.StockMarketIndexInfoMapper;
import com.itheima.stock.mapper.StockRtInfoMapper;
import com.itheima.stock.pojo.domain.InnerMarketDomain;
import com.itheima.stock.pojo.domain.PlateMarketDomain;
import com.itheima.stock.pojo.domain.StockUpdownDomain;
import com.itheima.stock.pojo.vo.StockInfoConfig;
import com.itheima.stock.service.MarketService;
//import com.itheima.stock.utils.DateTimeUtil;
import com.itheima.stock.vo.resp.PageResult;
import com.itheima.stock.vo.resp.R;
import com.itheima.stock.vo.resp.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MarketServiceImpl implements MarketService {

    @Autowired
    private StockInfoConfig stockInfoConfig;

    @Autowired
    private StockMarketIndexInfoMapper infoMapper;

    @Autowired
    private StockBlockRtInfoMapper rtInfoMapper;

    @Autowired
    private StockRtInfoMapper stockRtInfoMapper;

    @Override
    public R<List<InnerMarketDomain>> getInnerMarket() {
        // 获取当前时间下的上一次收盘时间
        // 目前使用假数据

        // DateTime dateTime = DateTimeUtil.getLastDate4Stock(DateTime.now());
        // Date date = dateTime.toDate();

        DateTime dateTime = DateTime.parse("2021-12-28 09:31:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        Date date = dateTime.toDate();
        List<String> innerList = stockInfoConfig.getInner();
        List<InnerMarketDomain> result = infoMapper.getInnerMarket(date, innerList);
        return R.ok(result);
    }

    @Override
    public R<List<PlateMarketDomain>> getPlateMarket() {
        DateTime dateTime = DateTime.parse("2021-12-21 09:30:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        Date date = dateTime.toDate();
        List<PlateMarketDomain> list = rtInfoMapper.getPlateMarket(date);
        return R.ok(list);
    }

    @Override
    public R<PageResult<StockUpdownDomain>> getAllMarkets(Integer page, Integer pageSize) {
        DateTime dateTime = DateTime.parse("2021-12-30 09:32:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        Date date = dateTime.toDate();
        // 分页 劫持查询操作
        PageHelper.startPage(page, pageSize);
        List<StockUpdownDomain> data = stockRtInfoMapper.getMarketByPage(date);
        PageInfo<StockUpdownDomain> dataPageInfo = new PageInfo<>(data);
        PageResult<StockUpdownDomain> result = new PageResult<>(dataPageInfo);
        return R.ok(result);
    }

    @Override
    public R<List<StockUpdownDomain>> getIncreaseMarket() {
        DateTime dateTime = DateTime.parse("2021-12-30 09:32:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        Date date = dateTime.toDate();
        List<StockUpdownDomain> dataList = stockRtInfoMapper.getIncreaseMarket(date);
        return R.ok(dataList);
    }

    @Override
    public R<Map<String, List>> getUpDownData() {
        DateTime startDate = DateTime.parse("2022-01-06 09:30:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        Date startTime = startDate.toDate();
        DateTime endDate = DateTime.parse("2022-01-06 14:25:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        Date endTime = endDate.toDate();
        List<Map> upList = rtInfoMapper.getUpDownData(startTime, endTime, 1);
        List<Map> downList = rtInfoMapper.getUpDownData(startTime, endTime, 2);
        HashMap<String, List> hashMap = new HashMap<>();
        hashMap.put("upList", upList);
        hashMap.put("downList", downList);
        return R.ok(hashMap);
    }

    /**
     * excel导出功能
     *
     * @param page     当前页数
     * @param pageSize 每页条数
     * @param res      响应对象
     */
    @Override
    public void downloadMarketExcel(Integer page, Integer pageSize, HttpServletResponse res) {
        PageResult<StockUpdownDomain> pageResult = this.getAllMarkets(page, pageSize).getData();
        List<StockUpdownDomain> domains = pageResult.getRows();

        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String fileName = "导出" + format + ".xlsx";

        res.setContentType("application/vnd.ms-excel");
        res.setCharacterEncoding("utf-8");
        try {
            res.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1"));
            EasyExcel.write(res.getOutputStream(), StockUpdownDomain.class).sheet("kk").doWrite(domains);
        } catch (IOException e) {
            log.error("导出涨幅榜数据出错。原因：{}", e.getMessage());
            // 重置response
            res.reset();
            res.setContentType("application/json");
            res.setCharacterEncoding("utf-8");
            R<Object> objectR = R.error(ResponseCode.ERROR);
            try {
                res.getWriter().println(new ObjectMapper().writeValueAsString(objectR));
            } catch (IOException ioException) {
                log.error("导出涨幅榜数据，返回JSON出错。原因：{}", ioException.getMessage());
            }
        }
    }
}
