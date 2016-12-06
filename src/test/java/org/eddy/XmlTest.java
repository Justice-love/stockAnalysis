package org.eddy;

import org.eddy.exception.JsoupException;
import org.eddy.xml.XmlContext;
import org.junit.Test;

/**
 * Created by eddy on 2016/12/6.
 */
public class XmlTest {

    @Test
    public void test() throws JsoupException {
        XmlContext context = new XmlContext();
        context.loadXml("configration.xml");
    }
}
