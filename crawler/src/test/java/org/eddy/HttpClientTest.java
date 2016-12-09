package org.eddy;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by eddy on 2016/12/9.
 */
public class HttpClientTest {

    @Test
    public void test() throws IOException {
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        httpClientConnectionManager.setMaxTotal(50);
        httpClientConnectionManager.setDefaultMaxPerRoute(5);
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(httpClientConnectionManager).build();
        HttpGet httpGet = new HttpGet("http://hq.sinajs.cn/list=sh601988");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        System.out.println(EntityUtils.toString(entity));
    }
}
