package org.eddy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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

}
