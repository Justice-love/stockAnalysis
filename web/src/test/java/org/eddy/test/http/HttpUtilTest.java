package org.eddy.test.http;

import com.alibaba.fastjson.JSONObject;
import org.eddy.ApplicationStart;
import org.eddy.config.StockSolverConfig;
import org.eddy.entity.pojo.HttpMessage;
import org.eddy.util.HttpUtil;
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
        HttpUtil.sendMsg(message);
    }
}
