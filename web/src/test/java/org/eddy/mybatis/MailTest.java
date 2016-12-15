package org.eddy.mybatis;

import org.apache.velocity.app.VelocityEngine;
import org.eddy.ApplicationStart;
import org.eddy.entity.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    JavaMailSender mailSender;
    @Autowired
    VelocityEngine velocityEngine;

    @Test
    public void testMail() {
        User user = new User();
        user.setId(1);
        user.setName("eddy");

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo("eddyxu1213@126.com");
                message.setFrom("sharter@126.com");
                message.setSubject("test");
                Map model = new HashMap();
                model.put("user", user);
                model.put("email", "sharter@126.com");
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "template/emailTemplate.vm", model);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }
}
