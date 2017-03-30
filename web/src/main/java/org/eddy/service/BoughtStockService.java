package org.eddy.service;

import org.eddy.entity.BoughtStock;
import org.eddy.entity.Stock;

import java.util.List;

/**
 * Created by eddy on 2016/12/17.
 */
public interface BoughtStockService {

    void insertOne(BoughtStock boughtStock);

    void deleteOne(int id);

    List<BoughtStock> selectAll();

    List<BoughtStock> selectByCode(String code);

    void sale(Stock stock);
}
