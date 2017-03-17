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

    int deleteByDate(String date);

    List<String> groupByStockCode(String date);

    String selectMaxTime(@Param("code") String code, @Param("date") String date);

    List<Stock> selectLastOnes(@Param("params") List<Stock> params);

    List<Stock> selectGroup(String date);

    List<Stock> selectGroupByStock(String date, Stock stock);

    List<Stock> selectSortedStocks(String code);

    List<Stock> selectSortedStockOneDate(@Param("code") String code, @Param("date") String date);
}
