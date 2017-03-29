package org.eddy.test.http;

import com.alibaba.fastjson.JSONObject;
import org.eddy.ApplicationStart;
import org.eddy.config.StockSolverConfig;
import org.eddy.entity.Stock;
import org.eddy.swing.entity.Swing;
import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.entity.ValidateSwing;
import org.eddy.swing.entity.groovy.BuyPrice;
import org.eddy.swing.entity.httpMessage.HttpMessage;
import org.eddy.util.HttpUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Date;

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
    public void test() throws IOException {
        HttpMessage message = new HttpMessage();
        message.setProtocol(HttpMessage.ProtocolEnum.HTTPS);
        message.setUrl(new StringBuilder(config.getUrl()).append("?").append(config.getArg()).append("=").append(config.getMainToken()).toString());
        message.setMessageTypeEnum(HttpMessage.MessageTypeEnum.text);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", "我就是我, 是不一样的烟火");
        message.setContent(jsonObject.toJSONString());
        boolean result = HttpUtil.sendMsg(message);
        Assert.assertTrue(result);
    }

    @Test
    public void test2() throws IOException {
        ValidateSwing validateSwing = new ValidateSwing(new Swing("id", "test", null, new BuyPrice()));
        validateSwing.setSuccess(true);
        Stock stock = new Stock();
        stock.setStockCode("sh601006");
        stock.setName("大秦铁路");
        stock.setPrice("10.1");
        stock.setUp("10%");
        stock.setDate("2017-03-07");
        stock.setTime("14:37:07");
        SwingValidateContext context = new SwingValidateContext(validateSwing, stock, null);
        HttpMessage message = new HttpMessage();
        message.setProtocol(HttpMessage.ProtocolEnum.HTTPS);
        message.setUrl(new StringBuilder(config.getUrl()).append("?").append(config.getArg()).append("=").append(config.getMainToken()).toString());
        message.setMessageTypeEnum(HttpMessage.MessageTypeEnum.markdown);
        message.setContent(HttpMessage.MessageTypeEnum.markdown.createBuyContent(context));
        boolean result = HttpUtil.sendMsg(message);
        Assert.assertTrue(result);

    }

    @Test
    public void test3() throws IOException {
        ValidateSwing validateSwing = new ValidateSwing(new Swing("id", "test", null, new BuyPrice()));
        validateSwing.setSuccess(true);
        Stock stock = new Stock();
        stock.setStockCode("sh601006");
        stock.setName("大秦铁路");
        stock.setPrice("10.1");
        stock.setUp("10%");
        stock.setDate("2017-03-07");
        stock.setTime("14:37:07");
        stock.setBoughtPrice("9.0");
        stock.setBoughtTime(new Date());
        SwingValidateContext context = new SwingValidateContext(validateSwing, stock, null);
        HttpMessage message = new HttpMessage();
        message.setProtocol(HttpMessage.ProtocolEnum.HTTPS);
        message.setUrl(new StringBuilder(config.getUrl()).append("?").append(config.getArg()).append("=").append(config.getMainToken()).toString());
        message.setMessageTypeEnum(HttpMessage.MessageTypeEnum.markdown);
        message.setContent(HttpMessage.MessageTypeEnum.markdown.createSaleContent(context));
        boolean result = HttpUtil.sendMsg(message);
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            HttpUtil.sendMsg(message);
        }
        Assert.assertTrue(result);
    }
}
