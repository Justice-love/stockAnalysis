package org.eddy.dao.mapper.stock;

import org.apache.ibatis.annotations.Mapper;
import org.eddy.entity.BoughtStock;

import java.util.List;

/**
 * Created by eddy on 2016/12/17.
 */
@Mapper
public interface BoughtStockMapper {

    int insertOne(BoughtStock boughtStock);

    int deleteOne(int id);

    List<BoughtStock> selectBought();

    List<BoughtStock> selectByCode(String code);
}
