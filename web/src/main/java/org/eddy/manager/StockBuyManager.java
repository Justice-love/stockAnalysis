package org.eddy.manager;

import org.eddy.entity.BoughtStock;
import org.eddy.entity.Stock;
import org.eddy.entity.StockWantBuy;

import java.util.List;

/**
 * Created by eddy on 16/12/16.
 */
public interface StockBuyManager {

    void needBuy();

    void needBuy(Stock stock);

    List<StockWantBuy> selectAll();

    void deleteOneById(int id);

    void buyStock(int id);

    List<BoughtStock> showBought();
}
