package org.eddy.schedule;

import org.eddy.manager.ComputerAndLoadHistoryStock;
import org.eddy.manager.CrawlStockManager;
import org.eddy.manager.StockBuyManager;
import org.eddy.manager.StockSaleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by eddy on 2016/12/10.
 */
@Component
@Configurable
@EnableScheduling
public class SpringJob {

    private static final Logger logger = LoggerFactory.getLogger(SpringJob.class);

    @Autowired
    private CrawlStockManager crawlStockManager;

    @Autowired
    private ComputerAndLoadHistoryStock computerAndLoadHistoryStock;

    @Autowired
    private StockBuyManager stockBuyManager;

    @Autowired
    private StockSaleManager stockSaleManager;

    @Scheduled(cron = "10 0-59 10-14 * * 1-5 ")
    public void crawlAllUrl() {
        try {
            crawlStockManager.crawlStock();
        } catch (Exception e) {
            logger.error("execute Schedule crawlAllUrl error", e);
        }
    }

    @Scheduled(cron = "10 15-59 9 * * 1-5 ")
    public void crawlAllUrlHalfOneHour() {
        try {
            crawlStockManager.crawlStock();
        } catch (Exception e) {
            logger.error("execute Schedule crawlAllUrl error", e);
        }
    }

    @Scheduled(cron = "0 1 15 * * 1-5 ")
    public void crawlAllUrlOneTime() {
        try {
            crawlStockManager.crawlStock();
        } catch (Exception e) {
            logger.error("execute Schedule crawlAllUrl error", e);
        }
    }

    @Scheduled(cron = "0 5 15 * * 1-5 ")
    public void computer() {
        try {
            computerAndLoadHistoryStock.computerAndLoad();
        } catch (Exception e) {
            logger.error("execute Schedule computer error", e);
        }
    }

    @Scheduled(cron = "10 0-59 10-14 * * 1-5 ")
    public void analysisToBuyStock() {
        try {
            stockBuyManager.needBuy();
        } catch (Exception e) {
            logger.error("execute Schedule analysisToBuyStock error", e);
        }
    }

    @Scheduled(cron = "10 15-59 9 * * 1-5 ")
    public void analysisToBuyStockHalfOneHour() {
        try {
            stockBuyManager.needBuy();
        } catch (Exception e) {
            logger.error("execute Schedule analysisToBuyStock error", e);
        }
    }

    @Scheduled(cron = "50 0-59 10-14 * * 1-5 ")
    public void analysisToSaleStock() {
        try {
            stockSaleManager.shouldSale();
        } catch (Exception e) {
            logger.error("execute Schedule analysisToSaleStock error", e);
        }
    }

    @Scheduled(cron = "50 15-59 9 * * 1-5 ")
    public void analysisToSaleStockHalfOneHour() {
        try {
            stockSaleManager.shouldSale();
        } catch (Exception e) {
            logger.error("execute Schedule analysisToSaleStock error", e);
        }
    }

}
