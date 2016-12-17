package org.eddy.service;

import org.eddy.entity.StockWantBuy;

import java.util.List;

/**
 * Created by eddy on 16/12/16.
 */
public interface StockWantBuyService {

    /**
     *
     * @param stockWantBuy
     * @return true:需要发消息
     */
    boolean insertOrUpdateNeedNotify(StockWantBuy stockWantBuy);

    List<StockWantBuy> selectAllToBuy();

}
