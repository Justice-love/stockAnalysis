package org.eddy.service.impl;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.velocity.app.VelocityEngine;
import org.eddy.service.NotifyService;
import org.eddy.swing.entity.SwingValidateContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by eddy on 16/12/16.
 */
@Component("email")
public class EmailNotifyService implements NotifyService {

    public static final String EMAIL_FROM = "stock@just-love.cn";

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;

    @Override
    public void notify(SwingValidateContext context, String ... others) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(others[1]);
                message.setFrom(EMAIL_FROM);
                message.setSubject(others[2]);
                Map model = new HashMap();
                model.put("context", context);
                model.put("time", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE));
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "template/"+ others[0] +".vm", model);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }
}
