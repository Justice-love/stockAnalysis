package org.eddy;

import org.eddy.exception.JsoupException;
import org.eddy.xml.XmlContext;
import org.eddy.xml.entity.Url;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by eddy on 2016/12/6.
 */
public class XmlTest {

    @Test
    public void test() throws JsoupException {
        XmlContext context = new XmlContext();
        List<Url> urlList = context.loadXml("configration.xml");
        Assert.assertEquals(1, urlList.size());
    }
}
