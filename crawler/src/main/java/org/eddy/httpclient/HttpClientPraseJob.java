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

import java.io.IOException;
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
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(pools).build();
        try {
            HttpGet httpGet = new HttpGet(url.getUrl());
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            temp = EntityUtils.toString(httpEntity);

            String content = temp;
            Stock result = new Stock();
            result.setStockCode(url.getUrl().split("=")[1]);
            if (!url.getTest().test(content, url)) {
                result.setHasError(true);
                result.setErrorContent(getContentWhenError(content));
                return result;
            }

            List<Url.UrlRule> ruleList =  url.getUrlRuleList();
            ruleList.stream().filter(r -> SelectType.HTTPCLIENT_TYPE.equals(r.getSelectType().getType())).forEach(r -> {
                String text = StringUtils.EMPTY;
                SelectType selectType = r.getSelectType();
                if (selectType == SelectType.computer) {
                    text = selectType.findElement(result, r.getExpression());
                } else {
                    text = selectType.findElement(content, r.getExpression());
                }
                try {
                    writePropertie(result, r.getProperty(), text);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            httpResponse.close();
            return result;
        } catch (IOException e) {
            throw new JsoupException(e);
        } catch (Exception e) {
            throw new JsoupException(e);
        }
    }

    private String getContentWhenError(String value) {
        String temp = value.substring(value.indexOf("\"") + 1);
        return temp.substring(0, temp.indexOf("\""));
    }
}
