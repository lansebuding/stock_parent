package com.itheima.stock.service.impl;

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
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
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
}
