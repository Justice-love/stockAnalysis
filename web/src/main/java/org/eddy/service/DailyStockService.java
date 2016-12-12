package org.eddy.service;

import org.eddy.entity.Stock;

import java.util.List;

/**
 * Created by eddy on 2016/12/12.
 */
public interface DailyStockService {

    void loadDailyStock(List<Stock> source);
}
