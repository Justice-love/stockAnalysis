package org.eddy.service;

import org.eddy.entity.Stock;

import java.util.Date;
import java.util.List;

/**
 * Created by eddy on 2016/12/11.
 */
public interface StockService {

    void loadStockPerMin(List<Stock> list);

    boolean isNeedLoad(Stock stock);

    List<Stock> computerDailyStocks();
}
