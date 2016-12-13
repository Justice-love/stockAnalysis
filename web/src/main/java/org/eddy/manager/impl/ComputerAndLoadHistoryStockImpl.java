package org.eddy.manager.impl;

import org.eddy.entity.Stock;
import org.eddy.manager.ComputerAndLoadHistoryStock;
import org.eddy.service.DailyStockService;
import org.eddy.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by eddy on 16/12/13.
 */
@Service
public class ComputerAndLoadHistoryStockImpl implements ComputerAndLoadHistoryStock {

    @Autowired
    private StockService stockService;

    @Autowired
    private DailyStockService dailyStockService;

    @Override
    @Transactional
    public void computerAndLoad() {
        List<Stock> source = stockService.computerDailyStocks();
        dailyStockService.loadDailyStock(source);
    }
}
