package com.itheima.stock.controller;

import cn.hutool.http.server.HttpServerResponse;
import com.itheima.stock.pojo.domain.InnerMarketDomain;
import com.itheima.stock.pojo.domain.PlateMarketDomain;
import com.itheima.stock.pojo.domain.StockUpdownDomain;
import com.itheima.stock.service.MarketService;
import com.itheima.stock.vo.resp.PageResult;
import com.itheima.stock.vo.resp.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Api("/api/quot")
@RestController
@RequestMapping("/api/quot")
public class MarketInfoController {

    @Autowired
    private MarketService marketService;

    @ApiOperation(value = "获取国内大盘最新数据", notes = "", httpMethod = "GET")
    @GetMapping("/index/all")
    public R<List<InnerMarketDomain>> getInnerMarket() {
        return marketService.getInnerMarket();
    }

    @ApiOperation(value = "", notes = "", httpMethod = "GET")
    @GetMapping("/sector/all")
    public R<List<PlateMarketDomain>> getPlateMarket() {
        return marketService.getPlateMarket();
    }

    /**
     * 分页获取个股详情
     * @param page 当前页数
     * @param pageSize 每页条数
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "page", value = "当前页数"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "每页条数")
    })
    @ApiOperation(value = "分页获取个股详情", notes = "分页获取个股详情", httpMethod = "GET")
    @GetMapping("/stock/all")
    public R<PageResult<StockUpdownDomain>> getAllMarkets(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                          @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return marketService.getAllMarkets(page, pageSize);
    }

    /**
     * 获取个股排行前4条
     */
    @ApiOperation(value = "获取个股排行前4条", notes = "获取个股排行前4条", httpMethod = "GET")
    @GetMapping("/stock/increase")
    public R<List<StockUpdownDomain>> getIncreaseMarket(){
        return marketService.getIncreaseMarket();
    }

    @ApiOperation(value = "", notes = "", httpMethod = "GET")
    @GetMapping("/stock/updown/count")
    public R<Map<String,List>> getUpDownData(){
        return marketService.getUpDownData();
    }

    /**
     * 导出功能开发
     * @param page 当前页数
     * @param pageSize 每页条数
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "page", value = "当前页数"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "每页条数")
    })
    @ApiOperation(value = "导出功能开发", notes = "导出功能开发", httpMethod = "GET")
    @GetMapping("/stock/export")
    public void downloadMarketExcel(@RequestParam(required = false, defaultValue = "1") Integer page,
                                    @RequestParam(required = false, defaultValue = "20") Integer pageSize,
                                    HttpServletResponse res){
        marketService.downloadMarketExcel(page, pageSize, res);
    }
}
