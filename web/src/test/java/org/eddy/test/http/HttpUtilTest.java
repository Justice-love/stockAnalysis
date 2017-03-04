package org.eddy.test.http;

import com.alibaba.fastjson.JSONObject;
import org.eddy.ApplicationStart;
import org.eddy.config.StockSolverConfig;
import org.eddy.entity.Stock;
import org.eddy.entity.pojo.HttpMessage;
import org.eddy.swing.entity.Swing;
import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.entity.ValidateSwing;
import org.eddy.swing.entity.Validater;
import org.eddy.swing.entity.groovy.BuyPrice;
import org.eddy.util.HttpUtil;
import org.eddy.util.MarkdownUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

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
        message.setUrl(new StringBuilder(config.getUrl()).append("?").append(config.getArg()).append("=").append(config.getToken()).toString());
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
        message.setUrl(new StringBuilder(config.getUrl()).append("?").append(config.getArg()).append("=").append(config.getToken()).toString());
        message.setMessageTypeEnum(HttpMessage.MessageTypeEnum.markdown);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "购买提醒");
        jsonObject.put("text", MarkdownUtil.createMarkdownContent(context));
        message.setContent(jsonObject.toJSONString());
        boolean result = HttpUtil.sendMsg(message);
        Assert.assertTrue(result);

    }
}
