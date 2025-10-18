package com.itheima.stock.service;

import com.itheima.stock.pojo.domain.InnerMarketDomain;
import com.itheima.stock.vo.resp.R;

import java.util.List;

public interface MarketService {
    R<List<InnerMarketDomain>> getInnerMarket();
}
