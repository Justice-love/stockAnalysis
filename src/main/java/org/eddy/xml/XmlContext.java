package org.eddy.xml;

import org.eddy.exception.JsoupException;
import org.eddy.xml.entity.Url;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by eddy on 16/12/5.
 */
public class XmlContext {

    public List<Url> loadXml(String filePath) throws JsoupException {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            builder.setEntityResolver(new ConfigrationDtdPathREsolver());
            Document document = builder.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath));
            Element root = document.getDocumentElement();
            return findUrls(root);
        } catch (ParserConfigurationException e) {
            throw new JsoupException(e.getMessage(), e);
        }  catch (SAXException e) {
            throw new JsoupException(e.getMessage(), e);
        } catch (IOException e) {
            throw new JsoupException(e.getMessage(), e);
        }
    }

    private List<Url> findUrls(Element root) {
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Url.TAG_NAME.equals(node.getNodeName())) {
            }
        }
        return null;
    }


}
