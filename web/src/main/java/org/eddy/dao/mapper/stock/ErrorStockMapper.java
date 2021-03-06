package org.eddy.dao.mapper.stock;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.eddy.entity.Stock;

import java.util.List;

/**
 * Created by eddy on 2016/12/11.
 */
@Mapper
public interface ErrorStockMapper {

    int insert(@Param("list") List<Stock> stocks);

    List<Stock> selectByCode(String stockCode);

    Integer countByCode(String stockCode);
}
