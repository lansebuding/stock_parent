package com.itheima.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author by itheima
 * @Date 2025/10/20
 * @Description 定义封装多大盘板块指数数据的实体类
 */
@Data
public class PlateMarketDomain {
    /**
     * 板块名称
     */
    private String name;

    /**
     * 板块编码
     */
    private String code;

    /**
     * 公司数量
     */
    private Integer companyNum;

    /**
     * 平均价格
     */
    private BigDecimal avgPrice;

    /**
     * 涨幅
     */
    private BigDecimal updownRate;

    /**
     * 交易量
     */
    private Long tradeAmt;

    /**
     * 交易总金额
     */
    private BigDecimal tradeVol;

    /**
     * 当前日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date curDate;
}
