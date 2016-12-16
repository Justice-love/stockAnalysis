package org.eddy.mybatis;

import org.apache.velocity.app.VelocityEngine;
import org.eddy.ApplicationStart;
import org.eddy.entity.Stock;
import org.eddy.entity.pojo.User;
import org.eddy.service.NotifyService;
import org.eddy.swing.entity.Swing;
import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.entity.Validater;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

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
        SwingValidateContext context = new SwingValidateContext(new Swing(null, null, null, Validater.buyCountPercent), new Stock("第一支", "sh000001"), null);
        context.addSwingChain(new Swing(null, null, null, Validater.buyPrice));
        context.addSwingChain(new Swing(null, null, null, Validater.defaultValidater));
        mailService.notify(context, "toBuyTemplate", "eddyxu1213@126.com", "第一支");
    }
}
