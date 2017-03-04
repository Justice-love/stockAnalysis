package org.eddy.test.http;

import org.eddy.ApplicationStart;
import org.eddy.config.StockSolverConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Justice-love on 2017/3/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationStart.class})
@ActiveProfiles(value="dev")
public class HttpUtilTest {

    @Autowired
    private StockSolverConfig config;

    @Test
    public void test() {

    }
}
