package org.eddy.manager.impl;

import org.eddy.entity.BoughtStock;
import org.eddy.entity.Stock;
import org.eddy.entity.StockWantBuy;
import org.eddy.manager.StockBuyManager;
import org.eddy.service.BoughtStockService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
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
    private BoughtStockService boughtStockService;

    @Autowired
    private SwingFlow swingFlow;

    @Override
    public void needBuy() {
        stockService.groupStock().forEach(stock -> analysis(stock));
    }

    @Override
    public void needBuy(Stock stock) {
        Assert.notNull(stock);
        stockService.groupStock(stock).forEach(s -> analysis(s));
    }

    private void analysis(Stock stock) {
        Assert.notNull(stock);
        List<Stock> stockList = stockService.selectSortedStocks(stock.getStockCode());
        stockList.addAll(dailyStockService.selectSortedStocks(stock.getStockCode()));
        SortedMap<String, List<Stock>> listSortedMap =  new TreeMap<>(stockList.stream().collect(Collectors.groupingBy(s -> s.getDate())));
        Swing swing = SwingContext.getContext().getSwings().get("buy").get(0);
        try {
            swingFlow.flow(listSortedMap, stockList.get(0), swing);
        } catch (Exception e) {
            logger.error("stock: " + stock, e);
        }
    }

    @Override
    public List<StockWantBuy> selectAll() {
        return stockWantBuyService.selectAllToBuy();
    }

    @Override
    @Transactional
    public void deleteOneById(int id) {
        stockWantBuyService.deleteById(id);
    }

    @Override
    @Transactional
    public void buyStock(int id) {
        StockWantBuy stockWantBuy = stockWantBuyService.selectById(id);
        List<Stock> stockList = stockService.selectSortedStocksOneDate(stockWantBuy.getStockCode(), stockWantBuy.getCurrentDate());
        Assert.notEmpty(stockList);
        Stock message = stockList.get(0);
        BoughtStock boughtStock = new BoughtStock();
        boughtStock.setName(message.getName());
        boughtStock.setStockCode(message.getStockCode());
        boughtStock.setBuyPrice(message.getPrice());
        boughtStock.setBuyTime(new Date());
        boughtStockService.insertOne(boughtStock);
        stockWantBuyService.deleteById(id);
    }

    @Override
    public List<BoughtStock> showBought() {
        return boughtStockService.selectAll();
    }
}
