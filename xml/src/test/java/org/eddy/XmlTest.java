package org.eddy;

import org.apache.commons.lang3.StringUtils;
import org.eddy.entity.Stock;
import org.eddy.entity.Url;
import org.eddy.exception.JsoupException;
import org.eddy.httpclient.HttpClientPraseJob;
import org.eddy.jsoup.JsoupParseJob;
import org.eddy.xml.XmlContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by eddy on 2016/12/6.
 */
public class XmlTest {

    @Test
    public void test() throws JsoupException {
        List<Url> urlList = XmlContext.getContext().getUrls();
//        Assert.assertEquals(2, urlList.size());
        ParseJob jsoup = new JsoupParseJob();
        ParseJob http = new HttpClientPraseJob();
        List<Stock> stockList = urlList.parallelStream().map(s -> {
            try {
                Stock stock =  null;
                if (StringUtils.equals(Url.HTTPCLIENT_TYPE, s.getType())) {
                    stock = http.crawlPage(s);
                } else if (StringUtils.equals(Url.JSOUP_TYPE, s.getType())) {
                    stock = jsoup.crawlPage(s);
                }
                return stock;
            } catch (JsoupException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());
        Assert.assertEquals(4000, stockList.size());
    }

    @Test
    public void test2() {
        List<Url> urlList = XmlContext.getContext().getUrls();
        System.out.println(urlList);
    }
}
