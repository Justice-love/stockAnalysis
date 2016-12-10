package org.eddy.xml;

import org.eddy.entity.SelectType;
import org.eddy.exception.JsoupException;
import org.eddy.entity.Url;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by eddy on 16/12/5.
 */
public class XmlContext {

    public List<Url> loadXml(String filePath) throws JsoupException {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            builder.setEntityResolver(new ConfigrationDtdPathResolver());
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

    private List<Url> findUrls(Element root){
        NodeList nodeList = root.getChildNodes();
        List<String> paths = new ArrayList<String>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Url.URL_TAG_NAME.equals(node.getNodeName()) && Node.ELEMENT_NODE == node.getNodeType()) {
                Element element = (Element) node;
                paths.add(element.getAttribute("path"));
            }
        }
        return paths.stream().map(s -> parseUrl(s)).collect(Collectors.toList());
    }

    private Url parseUrl(String path){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            builder.setEntityResolver(new UrlDtdPathResolver());
            Document document = builder.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));

            Element root = document.getDocumentElement();

            Url url = new Url(root.getAttribute("url"));
            url.setPathVariableClass(Optional.of(root.getAttribute("pathVariableClass")).orElse(null));
            url.setType(Optional.of(root.getAttribute("type")).orElse(Url.HTTPCLIENT_TYPE));

            Element test = (Element) root.getElementsByTagName("test").item(0);
            url.setTest(new Url.Test(SelectType.valueOf(Optional.of(test.getAttribute("selectType")).orElse("id")), Optional.of(test.getAttribute("expression")).orElse(null), Optional.of(test.getAttribute("validate")).orElse(null)));

            NodeList ruleList = root.getElementsByTagName(Url.RULE_TAG_NAME);
            List<Url.UrlRule> urlRules = new ArrayList<>(10);
            for (int i = 0; i < ruleList.getLength(); i++) {
                Node node = ruleList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    new Url.UrlRule();
                    urlRules.add(new Url.UrlRule(SelectType.valueOf(Optional.of(element.getAttribute("selectType")).orElse("id")), Optional.of(element.getAttribute("expression")).orElse(null), Optional.of(element.getAttribute("property")).orElse(null)));
                }
            }
            url.setUrlRuleList(urlRules);

            return url;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
