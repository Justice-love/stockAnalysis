package org.eddy.manager.impl;

import org.eddy.entity.Stock;
import org.eddy.entity.StockWantBuy;
import org.eddy.manager.StockBuyManager;
import org.eddy.service.DailyStockService;
import org.eddy.service.StockService;
import org.eddy.service.StockWantBuyService;
import org.eddy.swing.SwingContext;
import org.eddy.swing.entity.Swing;
import org.eddy.swing.flow.SwingFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by eddy on 16/12/16.
 */
@Service
public class StockBuyManagerImpl implements StockBuyManager {

    private static final Logger logger = LoggerFactory.getLogger(StockBuyManagerImpl.class);

    @Autowired
    private StockService stockService;

    @Autowired
    private DailyStockService dailyStockService;

    @Autowired
    private StockWantBuyService stockWantBuyService;

    @Autowired
    private SwingFlow swingFlow;

    @Override
    public void needBy() {
        stockService.groupStock().forEach(stock -> {
            List<Stock> stockList = stockService.selectSortedStocks(stock.getStockCode());
            stockList.addAll(dailyStockService.selectSortedStocks(stock.getStockCode()));
            SortedMap<String, List<Stock>> listSortedMap =  new TreeMap<>(stockList.stream().collect(Collectors.groupingBy(s -> s.getDate())));
            Swing swing = SwingContext.getContext().getSwings().get("buy").get(0);
            try {
                swingFlow.flow(listSortedMap, stockList.get(0), swing);
            } catch (Exception e) {
                logger.error("stock: " + stock, e);
            }
        });
    }

    @Override
    public List<StockWantBuy> selectAll() {
        return stockWantBuyService.selectAllToBuy();
    }

    @Override
    public void deleteOneById(int id) {
        stockWantBuyService.deleteById(id);
    }
}
