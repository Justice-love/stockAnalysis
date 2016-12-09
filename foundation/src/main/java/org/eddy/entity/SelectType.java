package org.eddy.entity;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eddy on 2016/12/4.
 */
public enum SelectType {
    id("jsoup") {
        @Override
        public String findElement(Object content, String expression) {
            Element element = (Element) content;
            Element idElement = element.getElementById(expression);
            return  findElement(idElement);
        }
    },
    css("jsoup") {
        @Override
        public String findElement(Object content, String expression) {
            Element element = (Element) content;
            Elements cssElements = element.getElementsByClass(expression);
            return findElement(cssElements.get(0));
        }
    },
    mix("jsoup") {
        Pattern onePattern = Pattern.compile("(\\w+)$");
        Pattern mutiPattern = Pattern.compile("(\\w+)\\((\\d+)\\)");
        @Override
        public String findElement(Object content, String expression) {
            Element element = (Element) content;
            String[] expressions = expression.split("\\|");
            for (String s : expressions) {
                if (isElementTag(s) && onePattern.matcher(s).matches()) {
                    Matcher matcher = onePattern.matcher(s);
                    matcher.find();
                    element = element.select(matcher.group(1)).first();
                } else if (isElementTag(s) && mutiPattern.matcher(s).matches()) {
                    Matcher matcher = mutiPattern.matcher(s);
                    matcher.find();
                    element = element.select(matcher.group(1)).get(Integer.parseInt(matcher.group(2)));
                } else {
                    element = element.select(s).first();
                }
            }
            return element.text();
        }

        private boolean isElementTag(String s) {
            return !(StringUtils.startsWith(s, ".") || StringUtils.startsWith(s, "#"));
        }
    }
    ;

    public static String JSOUP_TYPE = "jsoup";
    public static String HTTPCLIENT_TYPE = "httpClient";

    public String findElement(Object content, String expression) {
        throw new IllegalAccessError("not implement");
    }

    protected String findElement(Element element) {
        assert element != null;

        Elements children = element.children();
        if (children.size() < 1) return element.text();
        else return findElement(children.get(0));
    }

    public String getType() {
        return this.type;
    }

    private String type;

    private SelectType(String type) {
        this.type = type;
    }
}
