package org.eddy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Test
    public void test5() throws IOException {
        Document doc = Jsoup.connect("http://justice-love.com/").get();
        Elements elements = doc.select("body");
        Assert.assertEquals(1, elements.size());
    }

    @Test
    public void test6() {
        String test1 = "table";
        String test2 = "asdfasdf(10)";
        Pattern pattern = Pattern.compile("(\\w+)$");
//        Pattern pattern = Pattern.compile("(\\w+)\\((\\d+)\\)");
        Matcher matcher = pattern.matcher(test1);
        Matcher matcher2 = pattern.matcher(test2);
        if (matcher2.find()){
            System.out.println(matcher2.group(1));
//            System.out.println(matcher.group(2));
        }
        System.out.println(matcher2.groupCount());
        System.out.println(matcher2.group());
    }

    @Test
    public void test7() throws IOException {
        Document doc = Jsoup.connect("http://d.10jqka.com.cn/v2/realhead/hs_000750/last").get();
        Element element = doc.getElementById("hexm_curPrice");
        System.out.println(element.text());
    }
}
