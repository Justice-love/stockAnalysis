package org.eddy.swing;

import org.eddy.swing.entity.Swing;
import org.eddy.swing.entity.Validater;
import org.eddy.swing.entity.exception.SwingException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by eddy on 2016/12/13.
 */
public class SwingContext {

    private Map<String, List<Swing>> swings;

    private static SwingContext context = new SwingContext("config.xml");

    private SwingContext(String filePath){
        try {
            this.swings = loadContext(filePath);
        } catch (SwingException e) {
            throw new RuntimeException(e);
        }
    }

    public static SwingContext getContext() {
        return context;
    }

    public Map<String, List<Swing>> getSwings() {
        return this.swings;
    }

    private Map<String, List<Swing>> loadContext(String path) throws SwingException{
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            builder.setEntityResolver(new ConfigDtdPathResolver());
            Document document = builder.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            List<String> paths = new ArrayList<>(10);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    paths.add(element.getAttribute("path"));
                }
            }
            return paths.stream().map(s -> {
                try {
                    return parse(s);
                } catch (SwingException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }).flatMap(s -> s.stream()).collect(Collectors.groupingBy(s -> s.getId()));
        } catch (Exception e) {
            throw new SwingException(e.getMessage(), e);
        }
    }

    private List<Swing> parse(String s) throws SwingException{
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            builder.setEntityResolver(new SwingDtdPathResolver());
            Document document = builder.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(s));
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            List<Swing> result = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    result.add(parseSwing(node));
                }
            }
            return result;
        } catch (Exception e) {
            throw new SwingException(e.getMessage(), e);
        }
    }

    private Swing parseSwing(Node node) {
        Element element = (Element) node;
        Swing swing = new Swing(Optional.ofNullable(element.getAttribute("id")).orElse(""), Optional.ofNullable(element.getAttribute("expression")).orElse(""), Validater.valueOf(Optional.ofNullable(element.getAttribute("validateType")).orElse("defaultValidate")));
        Element child = findChild(element);
        if (null != child) {
            swing.setChild(parseSwing(child));
        }
        swing.setAutoTrigger(Optional.ofNullable(element.getAttribute("autoTrigger")).map(s -> Boolean.parseBoolean(s)).orElse(false));
        swing.setExecutor(Optional.ofNullable(element.getAttribute("executor")).orElse(""));
        swing.setOrElse(Optional.ofNullable(element.getAttribute("orElse")).orElse(""));
        return swing;
    }

    private Element findChild(Element element) {
        if (!element.hasChildNodes()) return null;
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                return (Element) node;
            }
        }
        return null;
    }

}
