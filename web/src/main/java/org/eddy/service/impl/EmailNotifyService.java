package org.eddy.service.impl;

import org.eddy.service.NotifyService;
import org.eddy.swing.entity.SwingValidateContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Locale;
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
    private TemplateEngine templateEngine;

    @Override
    public void notify(SwingValidateContext context, String ... others) {
        final Context ctx = new Context(Locale.SIMPLIFIED_CHINESE);
        ctx.setVariable("context", context);
        ctx.setVariable("pushTime", new Date());
        final String htmlContent = this.templateEngine.process("mail/"+others[0], ctx);
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            message.setFrom(EMAIL_FROM);
            message.setTo(others[1]);
            message.setSubject(others[2]);
            message.setText(htmlContent, true);
            this.mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
