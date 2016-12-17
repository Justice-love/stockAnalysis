package org.eddy.mybatis;

import org.eddy.ApplicationStart;
import org.eddy.entity.Stock;
import org.eddy.service.NotifyService;
import org.eddy.swing.entity.Swing;
import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.entity.Validater;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by eddy on 2016/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationStart.class})
@ActiveProfiles(value="dev")
public class MailTest {

    @Autowired
    @Qualifier("email")
    NotifyService mailService;

    @Test
    public void testMail() {
        Stock stock = new Stock("第一支", "sh000001");
        stock.setPrice("12.38");
        stock.setUp("3.7%");
        stock.setDate("2016");
        stock.setTime("17:00");
        SwingValidateContext context = new SwingValidateContext(new Swing("id", null, null, Validater.buyCountPercent), stock, null);
        context.addSwingChain(new Swing(null, null, null, Validater.buyPrice));
        context.addSwingChain(new Swing(null, null, null, Validater.defaultValidater));
        mailService.notify(context, "toBuyTemplate", "eddyxu1213@126.com", "第一支");
    }
}
