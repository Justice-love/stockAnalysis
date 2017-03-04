package org.eddy.entity.pojo;

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
        markdown;
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
