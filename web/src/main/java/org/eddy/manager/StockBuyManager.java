package org.eddy.manager;

import org.eddy.entity.StockWantBuy;

import java.util.List;

/**
 * Created by eddy on 16/12/16.
 */
public interface StockBuyManager {

    void needBy();

    List<StockWantBuy> selectAll();

    void deleteOneById(int id);
}
