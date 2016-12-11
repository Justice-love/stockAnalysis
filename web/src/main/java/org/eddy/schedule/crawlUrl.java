package org.eddy.schedule;

import org.eddy.manager.CrawlStockManager;
import org.jsoup.nodes.Element;
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
public class crawlUrl {

    @Autowired
    private CrawlStockManager crawlStockManager;

    @Scheduled(fixedRate = 1_000,initialDelay = 3_000)
    public void crawlAllUrl() {
        try {
            crawlStockManager.crawlStock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
