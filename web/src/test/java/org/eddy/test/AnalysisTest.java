package org.eddy.test;

import org.eddy.ApplicationStart;
import org.eddy.manager.StockBuyManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by eddy on 2016/12/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationStart.class})
@ActiveProfiles(value="pro")
@Transactional
public class AnalysisTest {
    @Autowired
    private StockBuyManager stockBuyManager;

    @Test
    public void test() {
        stockBuyManager.needBy();
    }
}
