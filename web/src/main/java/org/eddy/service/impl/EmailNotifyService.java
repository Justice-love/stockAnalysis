package org.eddy.service.impl;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.velocity.app.VelocityEngine;
import org.eddy.service.NotifyService;
import org.eddy.swing.entity.SwingValidateContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by eddy on 16/12/16.
 */
@Component("email")
public class EmailNotifyService implements NotifyService {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotifyService.class);

    private BlockingQueue<NotifyContext> blockingQueue = new LinkedBlockingQueue<NotifyContext>();

    private BlockingQueue<NotifyContext> errorTry = new LinkedBlockingQueue<NotifyContext>();

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

    @Override
    public void asyncNotify(SwingValidateContext context, String... others) {
        if (!blockingQueue.offer(new NotifyContext(context, others))) {
            try {
                blockingQueue.put(new NotifyContext(context, others));
            } catch (InterruptedException e) {
                logger.error("put message error", e);
            }
        }
    }

    @PostConstruct
    public void init() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            while (true) {
                NotifyContext context = null;
                try {
                    context = blockingQueue.take();
                    EmailNotifyService.this.notify(context.getContext(), context.getOthers());
                } catch (InterruptedException e) {
                    logger.error("asyncNotify", e);
                } catch (Exception e) {
                    if (null != context) {
                        errorTry.offer(context);
                    }
                }
            }
        });

        executorService.execute(() -> {
            while (true) {
                NotifyContext context = null;
                try {
                    context = errorTry.take();
                    EmailNotifyService.this.notify(context.getContext(), context.getOthers());
                } catch (InterruptedException e) {
                    logger.error("asyncNotify", e);
                } catch (Exception e) {
                    if (null != context && context.getTime() < 3) {
                        context.setTime(context.getTime() + 1);
                        errorTry.offer(context);
                    }
                }
            }
        });
    }

    public class NotifyContext {
        private SwingValidateContext context;
        private String[] others;
        private int time = 1;

        NotifyContext(SwingValidateContext context, String[] others) {
            this.context = context;
            this.others = others;
        }

        int getTime() {
            return time;
        }

        void setTime(int time) {
            this.time = time;
        }

        SwingValidateContext getContext() {
            return context;
        }

        void setContext(SwingValidateContext context) {
            this.context = context;
        }

        String[] getOthers() {
            return others;
        }

        void setOthers(String[] others) {
            this.others = others;
        }
    }
}
