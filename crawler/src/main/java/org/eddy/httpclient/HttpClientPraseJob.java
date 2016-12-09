package org.eddy.httpclient;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.eddy.ParseJob;
import org.eddy.entity.SelectType;
import org.eddy.entity.Stock;
import org.eddy.entity.Url;
import org.eddy.exception.JsoupException;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by eddy on 2016/12/9.
 */
public class HttpClientPraseJob extends ParseJob {

    private static PoolingHttpClientConnectionManager pools;

    public HttpClientPraseJob() {
        if (pools == null) {
            pools = new PoolingHttpClientConnectionManager();
            pools.setMaxTotal(50);
            pools.setDefaultMaxPerRoute(5);
        }
    }

    @Override
    public Stock crawlPage(Url url) throws JsoupException {
        String temp = StringUtils.EMPTY;
        try(CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(pools).build()) {
            HttpGet httpGet = new HttpGet(url.getAjaxUrl());
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            temp = EntityUtils.toString(httpEntity);

            Stock result = new Stock();
            String content = temp;
            List<Url.UrlRule> ruleList =  url.getUrlRuleList();
            ruleList.stream().filter(r -> SelectType.HTTPCLIENT_TYPE.equals(r.getSelectType().getType())).forEach(r -> {
                String text = r.getSelectType().findElement(content, r.getExpression());
                try {
                    writePropertie(result, r.getProperty(), text);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            return result;
        } catch (IOException e) {
            throw new JsoupException(e);
        } catch (Exception e) {
            throw new JsoupException(e);
        }
    }
}
