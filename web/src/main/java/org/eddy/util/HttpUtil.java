package org.eddy.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.eddy.swing.entity.httpMessage.HttpMessage;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Justice-love on 2017/3/4.
 */
public class HttpUtil {

    private static PoolingHttpClientConnectionManager pools;

    static {
        if (pools == null) {
            pools = new PoolingHttpClientConnectionManager();
            pools.setMaxTotal(50);
            pools.setDefaultMaxPerRoute(5);
        }
    }

    public static boolean sendMsg(HttpMessage message) throws IOException {
        Objects.requireNonNull(message, "message is null");
        if (HttpMessage.ProtocolEnum.HTTPS == message.getProtocol()) {
            return sendHttpsMsg(message);
        } else if (HttpMessage.ProtocolEnum.HTTP == message.getProtocol()) {
            return sendHttpMsg(message);
        }
        return false;
    }

    private static boolean sendHttpMsg(HttpMessage message) throws IOException {
        try(CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(pools).build()) {
            HttpPost post = new HttpPost(message.getUrl());
            post.setHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity(buildJsonMessage(message), "UTF-8"));
            HttpResponse httpresponse = httpClient.execute(post);
            return convertResponse(httpresponse.getEntity());
        }
    }

    private static boolean sendHttpsMsg(HttpMessage message) throws IOException {
        return sendHttpMsg(message);
    }

    private static boolean convertResponse(HttpEntity response) throws IOException {
        String strResponse = EntityUtils.toString(response);
        JSONObject jsonResponse = JSONObject.parseObject(strResponse);
        return jsonResponse.containsKey("errcode") && jsonResponse.getIntValue("errcode") == 0;
    }

    private static String buildJsonMessage(HttpMessage message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msgtype", message.getMessageTypeEnum().name());
        jsonObject.put(message.getMessageTypeEnum().name(), message.getContent());
        JSONObject at = new JSONObject();
        at.put("isAtAll", true);
        jsonObject.put("at", at);
        return jsonObject.toJSONString();
    }
}
