package com.eddy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by admin on 2016/12/4.
 */
public class JsoupTest {

    @Test
    public void test1() throws IOException {
        Document doc = Jsoup.connect("http://justice-love.com/").get();
        Element btn = doc.select(".btn").select(".zoombtn").get(0);
        Assert.assertEquals("About", btn.text());
    }

    @Test
    public void test2() throws IOException {
        Document doc = Jsoup.connect("http://justice-love.com/").get();
        Elements elements = doc.select(".title");
        String text = elements.get(0).getElementsByAttributeValue("class", "zoombtn").get(0).children().get(0).text();
        Assert.assertEquals("轶 & 霄", text);
    }

    @Test
    public void test3() throws IOException {
        Document doc = Jsoup.connect("http://justice-love.com/").get();
        Elements elements = doc.getElementsByClass("title");
        Assert.assertEquals(4, elements.size());
    }

    @Test
    public void test4() {
        String s = "a";
        assert null != s;
    }
}
