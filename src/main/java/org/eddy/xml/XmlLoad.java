package org.eddy.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by eddy on 16/12/5.
 */
public class XmlLoad {

    public void loadXml() throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        builder.setEntityResolver(new DtdPathREsolver());
//        Document document = builder.parse(null);
//        Element root = document.getDocumentElement();
    }
}
