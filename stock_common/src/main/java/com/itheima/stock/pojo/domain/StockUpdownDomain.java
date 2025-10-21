package com.itheima.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author by itheima
 * @Date 2022/2/28
 * @Description 股票涨跌信息
 */
@ApiModel(description = "股票涨跌信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockUpdownDomain {
    /**
     * 股票编码
     */
    @ApiModelProperty(value = "股票编码", position = 1)
    private String code;

    /**
     * 股票名称
     */
    @ApiModelProperty(value = "股票名称", position = 2)
    private String name;

    /**
     * 前收盘价
     */
    @ApiModelProperty(value = "前收盘价", position = 3)
    private BigDecimal preClosePrice;

    /**
     * 交易金额
     */
    @ApiModelProperty(value = "交易金额", position = 4)
    private BigDecimal tradePrice;

    /**
     * 涨跌
     */
    @ApiModelProperty(value = "涨跌", position = 5)
    private BigDecimal increase;

    /**
     * 涨幅
     */
    @ApiModelProperty(value = "涨幅", position = 6)
    private BigDecimal upDown;

    /**
     * 振幅
     */
    @ApiModelProperty(value = "振幅", position = 7)
    private BigDecimal amplitude;

    /**
     * 交易量
     */
    @ApiModelProperty(value = "交易量", position = 8)
    private Long tradeAmt;

    /**
     * 交易金额
     */
    @ApiModelProperty(value = "交易金额", position = 9)
    private BigDecimal tradeVol;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期", position = 10)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date curDate;
}