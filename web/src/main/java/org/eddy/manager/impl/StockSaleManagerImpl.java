package org.eddy.manager.impl;

import org.eddy.entity.Stock;
import org.eddy.manager.StockSaleManager;
import org.eddy.service.BoughtStockService;
import org.eddy.service.DailyStockService;
import org.eddy.service.StockService;
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
 * Created by eddy on 2016/12/17.
 */
@Service
public class StockSaleManagerImpl implements StockSaleManager {

    private static final Logger logger = LoggerFactory.getLogger(StockSaleManagerImpl.class);

    @Autowired
    private BoughtStockService boughtStockService;

    @Autowired
    private StockService stockService;

    @Autowired
    private DailyStockService dailyStockService;

    @Autowired
    private SwingFlow swingFlow;

    @Override
    public void shouldSale() {
        boughtStockService.selectAll().stream().forEach(boughtStock -> {
            List<Stock> stockList = stockService.selectSortedStocks(boughtStock.getStockCode());
            stockList.addAll(dailyStockService.selectSortedStocks(boughtStock.getStockCode()));
            SortedMap<String, List<Stock>> listSortedMap =  new TreeMap<>(stockList.stream().collect(Collectors.groupingBy(s -> s.getDate())));
            Swing swing = SwingContext.getContext().getSwings().get("sale").get(0);
            try {
                Stock stock = stockList.get(0);
                stock.setBoughtPrice(boughtStock.getBuyPrice());
                stock.setBoughtTime(boughtStock.getBuyTime());
                swingFlow.flow(listSortedMap, stock, swing);
            } catch (Exception e) {
                logger.error("boughtStock: " + boughtStock, e);
            }
        });
    }
}
