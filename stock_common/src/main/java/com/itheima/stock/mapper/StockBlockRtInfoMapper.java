package com.itheima.stock.mapper;

import com.itheima.stock.pojo.domain.PlateMarketDomain;
import com.itheima.stock.pojo.entity.StockBlockRtInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Admin
 * @description 针对表【stock_block_rt_info(股票板块详情信息表)】的数据库操作Mapper
 * @createDate 2025-10-10 21:42:40
 * @Entity com.itheima.stock.pojo.entity.StockBlockRtInfo
 */
public interface StockBlockRtInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockBlockRtInfo record);

    int insertSelective(StockBlockRtInfo record);

    StockBlockRtInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockBlockRtInfo record);

    int updateByPrimaryKey(StockBlockRtInfo record);

    List<PlateMarketDomain> getPlateMarket(Date date);

    List<Map> getUpDownData(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("flag") int flag);
}
