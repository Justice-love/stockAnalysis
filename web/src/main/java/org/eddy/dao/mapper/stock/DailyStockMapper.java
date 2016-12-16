package org.eddy.dao.mapper.stock;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.eddy.entity.Stock;

import java.util.List;

/**
 * Created by eddy on 2016/12/12.
 */
@Mapper
public interface DailyStockMapper {

    int insert(@Param("list") List<Stock> stocks);

    List<Stock> selectSortedStocks(String code);
}
