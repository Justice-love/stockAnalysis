package org.eddy.schedule;

import org.eddy.manager.ComputerAndLoadHistoryStock;
import org.eddy.manager.CrawlStockManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by eddy on 2016/12/10.
 */
@Component
@Configurable
//@EnableScheduling
public class crawlUrl {

    private static final Logger logger = LoggerFactory.getLogger(crawlUrl.class);

    @Autowired
    private CrawlStockManager crawlStockManager;

    @Autowired
    private ComputerAndLoadHistoryStock computerAndLoadHistoryStock;

    @Scheduled(fixedDelay = 60_000,initialDelay = 3_000)
    public void crawlAllUrl() {
        try {
            crawlStockManager.crawlStock();
        } catch (Exception e) {
            logger.error("execute Schedule crawlAllUrl error", e);
        }
    }

    public void computer() {
        try {
            computerAndLoadHistoryStock.computerAndLoad();
        } catch (Exception e) {
            logger.error("execute Schedule computer error", e);
        }
    }

}
