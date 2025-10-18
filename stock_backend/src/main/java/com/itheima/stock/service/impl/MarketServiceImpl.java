package com.itheima.stock.service.impl;

import com.itheima.stock.mapper.StockMarketIndexInfoMapper;
import com.itheima.stock.pojo.domain.InnerMarketDomain;
import com.itheima.stock.pojo.vo.StockInfoConfig;
import com.itheima.stock.service.MarketService;
//import com.itheima.stock.utils.DateTimeUtil;
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
}
