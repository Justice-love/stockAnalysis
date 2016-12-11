package org.eddy.manager.impl;

import org.eddy.entity.Stock;
import org.eddy.manager.CrawlStockManager;
import org.eddy.service.CrawlerService;
import org.eddy.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by eddy on 2016/12/11.
 */
@Service
@Profile("pro")
public class CrawlStockManagerImpl implements CrawlStockManager {

    @Autowired
    private CrawlerService crawlerService;

    @Autowired
    private StockService stockService;

    @Override
    public void crawlStock() {
        List<Stock> pendingInsert = Optional.of(crawlerService.crawlStock()).orElse(Arrays.asList()).stream().filter(s -> stockService.isNeedLoad(s)).collect(Collectors.toList());
        stockService.loadStockPerMin(pendingInsert);
    }
}
