package org.eddy.test;

import org.eddy.ApplicationStart;
import org.eddy.dao.mapper.swing.StockRuleSwingMapper;
import org.eddy.swing.entity.Swing;
import org.eddy.util.SwingUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by eddy on 2016/12/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationStart.class})
@ActiveProfiles(value="dev")
@Transactional
public class DbSwingTest {

    @Autowired
    private StockRuleSwingMapper stockRuleSwingMapper;

    @Test
    public void test() {
        List<Swing> swingList = SwingUtil.assemble(stockRuleSwingMapper.selectAll());
        Assert.assertEquals(2, swingList.size());
    }
}
