package org.eddy.xml;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Created by eddy on 16/12/5.
 */
public class DtdPathREsolver implements EntityResolver {
    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        if (systemId != null) {
            return new InputSource(Thread.currentThread().getContextClassLoader().getResourceAsStream("rule/parseRule.dtd"));
        }
        return null;
    }
}
