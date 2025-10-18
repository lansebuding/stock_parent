package com.itheima.stock.controller;

import com.itheima.stock.pojo.domain.InnerMarketDomain;
import com.itheima.stock.service.MarketService;
import com.itheima.stock.vo.resp.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quot")
@Api(tags = "股票数据控制器")
public class MarketInfoController {

    @Autowired
    private MarketService marketService;

    @GetMapping("/index/all")
    @ApiOperation("获取国内大盘最新数据")
    public R<List<InnerMarketDomain>> getInnerMarket(){
        return marketService.getInnerMarket();
    }
}
