package org.eddy.swing.entity.httpMessage;

import com.alibaba.fastjson.JSONObject;
import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.util.MarkdownUtil;

/**
 * Created by Justice-love on 2017/3/4.
 */
public class HttpMessage {

    private String url;
    private ProtocolEnum protocol;
    private String content;
    private MessageTypeEnum messageTypeEnum;

    public enum MessageTypeEnum {
        text,
        link,
        markdown {
            @Override
            public String createBuyContent(SwingValidateContext context) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("title", "购买提醒");
                jsonObject.put("text", MarkdownUtil.createMarkdownBuyInContent(context));
                return jsonObject.toJSONString();
            }

            @Override
            public String createSaleContent(SwingValidateContext context) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("title", "卖出提醒");
                jsonObject.put("text", MarkdownUtil.createMarkdownSaleOutContent(context));
                return jsonObject.toJSONString();
            }
        };

        public String createBuyContent(SwingValidateContext context) {
            throw new UnsupportedOperationException("not support");
        }

        public String createSaleContent(SwingValidateContext context) {
            throw new UnsupportedOperationException("not support");
        }
    }

    public enum ProtocolEnum {
        HTTP,
        HTTPS;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ProtocolEnum getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolEnum protocol) {
        this.protocol = protocol;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageTypeEnum getMessageTypeEnum() {
        return messageTypeEnum;
    }

    public void setMessageTypeEnum(MessageTypeEnum messageTypeEnum) {
        this.messageTypeEnum = messageTypeEnum;
    }
}
