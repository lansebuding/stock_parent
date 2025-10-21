package com.itheima.stock.service;

import com.itheima.stock.pojo.domain.InnerMarketDomain;
import com.itheima.stock.pojo.domain.PlateMarketDomain;
import com.itheima.stock.pojo.domain.StockUpdownDomain;
import com.itheima.stock.vo.resp.PageResult;
import com.itheima.stock.vo.resp.R;

import java.util.List;
import java.util.Map;

public interface MarketService {
    R<List<InnerMarketDomain>> getInnerMarket();

    R<List<PlateMarketDomain>> getPlateMarket();

    R<PageResult<StockUpdownDomain>> getAllMarkets(Integer page, Integer pageSize);

    R<List<StockUpdownDomain>> getIncreaseMarket();

    R<Map<String, List>> getUpDownData();
}
