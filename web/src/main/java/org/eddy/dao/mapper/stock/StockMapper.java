package org.eddy.dao.mapper.stock;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.eddy.entity.Stock;

import java.util.List;

/**
 * Created by eddy on 2016/12/11.
 */
@Mapper
public interface StockMapper {

    int insert(@Param("list") List<Stock> stocks);

    List<Stock> selectSotckByNameDateAndTime(@Param("stockCode") String stockCode, @Param("date") String date, @Param("time") String time);

    Integer countByNameDateAndTime(@Param("stockCode") String stockCode, @Param("date") String date, @Param("time") String time);

    String selectMaxDate();

    List<String> groupByDate();

    List<Stock> selectStatisticStock(@Param("date") String date);

    List<Stock> selectLastStockOneDay(String date);

    int deleteByDate(String date);
}
