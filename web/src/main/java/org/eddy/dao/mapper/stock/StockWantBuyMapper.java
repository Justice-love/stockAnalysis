package org.eddy.dao.mapper.stock;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.eddy.entity.StockWantBuy;

import java.util.Date;
import java.util.List;

/**
 * Created by eddy on 16/12/16.
 */
@Mapper
public interface StockWantBuyMapper {

    int insert(StockWantBuy stock);

    List<StockWantBuy> selectByNameAndCode(@Param("name") String name, @Param("code") String code);

    int updateById(@Param("id") int id, @Param("now") Date now, @Param("item") StockWantBuy stockWantBuy);

    int updateByIdWithPush(@Param("id") int id, @Param("now") Date now, @Param("push") Date push, @Param("item") StockWantBuy stockWantBuy);

    List<StockWantBuy> selectAll();

    int deleteById(int id);

    StockWantBuy selectById(int id);
}
