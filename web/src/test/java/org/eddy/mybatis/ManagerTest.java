package org.eddy.mybatis;

import org.eddy.ApplicationStart;
import org.eddy.manager.CrawlStockManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by eddy on 2016/12/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationStart.class})
@ActiveProfiles(value="pro")
public class ManagerTest {

    @Autowired
    private CrawlStockManager crawlStockManager;

    @Test
    public void test() {
        crawlStockManager.crawlStock();
    }
}
