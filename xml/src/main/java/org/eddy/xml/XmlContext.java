package org.eddy.xml;

import org.apache.commons.lang3.StringUtils;
import org.eddy.entity.SelectType;
import org.eddy.exception.JsoupException;
import org.eddy.entity.Url;
import org.eddy.xml.org.eddy.extend.define.UrlExtendProvider;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by eddy on 16/12/5.
 */
public class XmlContext {

    private List<Url> urls;

    private static XmlContext context = new XmlContext("configration.xml");

    private XmlContext(String filePath){
        try {
            this.urls = loadXml(filePath);
        } catch (JsoupException e) {
            throw new RuntimeException(e);
        }
    }

    public static XmlContext getContext() {
        return context;
    }

    public List<Url> getUrls() {
        return this.urls;
    }

    public List<Url> loadXml(String filePath) throws JsoupException {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            builder.setEntityResolver(new ConfigrationDtdPathResolver());
            Document document = builder.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath));
            Element root = document.getDocumentElement();
            return findUrls(root).stream().map(u -> {
                return excuteExtend(u);
            }).flatMap(urls -> {
                return urls.stream();
            }).collect(Collectors.toList());
        } catch (Exception e) {
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
            url.setPathVariableClass(Optional.ofNullable(root.getAttribute("class")).orElse(null));
            url.setType(Optional.ofNullable(root.getAttribute("type")).orElse(Url.HTTPCLIENT_TYPE));

            Element test = (Element) root.getElementsByTagName("test").item(0);
            url.setTest(new Url.Test(SelectType.valueOf(Optional.ofNullable(test.getAttribute("selectType")).orElse("id")), Optional.ofNullable(test.getAttribute("expression")).orElse(null), Optional.ofNullable(test.getAttribute("validate")).orElse(null)));

            NodeList ruleList = root.getElementsByTagName(Url.RULE_TAG_NAME);
            List<Url.UrlRule> urlRules = new ArrayList<>(10);
            for (int i = 0; i < ruleList.getLength(); i++) {
                Node node = ruleList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    new Url.UrlRule();
                    urlRules.add(new Url.UrlRule(SelectType.valueOf(Optional.ofNullable(element.getAttribute("selectType")).orElse("id")), Optional.ofNullable(element.getAttribute("expression")).orElse(null), Optional.ofNullable(element.getAttribute("property")).orElse(null), Optional.ofNullable(element.getAttribute("skipTest")).map(s -> Boolean.parseBoolean(s)).orElse(false)));
                }
            }
            url.setUrlRuleList(urlRules);

            return url;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public List<Url> excuteExtend(Url ori) {
        if (StringUtils.isBlank(ori.getPathVariableClass())) {
            return Arrays.asList(ori);
        }
        try {
            Class glass = Class.forName(ori.getPathVariableClass());
            if (UrlExtendProvider.class.isAssignableFrom(glass)) {
                UrlExtendProvider urlExtendProvider = (UrlExtendProvider) glass.newInstance();
                return urlExtendProvider.extend(ori);
            } else {
                return Arrays.asList(ori);
            }
        } catch (Exception e) {
            return Arrays.asList(ori);
        }
    }

}
