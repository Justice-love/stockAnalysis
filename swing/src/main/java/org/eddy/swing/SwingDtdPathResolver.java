package org.eddy.swing;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Created by eddy on 2016/12/7.
 */
public class SwingDtdPathResolver implements EntityResolver {
    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        if (systemId != null) {
            return new InputSource(Thread.currentThread().getContextClassLoader().getResourceAsStream("dtd/swing.dtd"));
        }
        return null;
    }
}
