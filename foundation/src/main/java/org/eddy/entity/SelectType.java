package org.eddy.entity;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by eddy on 2016/12/4.
 */
public enum SelectType {
    id {
        @Override
        public Element findElement(Element element, String expression) {
            Element idElement = element.getElementById(expression);
            return  findElement(idElement);
        }
    },
    css {
        @Override
        public Element findElement(Element element, String expression) {
            Elements cssElements = element.getElementsByClass(expression);
            return findElement(cssElements.get(0));
        }
    },
    mix {
        Pattern onePattern = Pattern.compile("(\\w+)");
        Pattern mutiPattern = Pattern.compile("(\\w+)\\((\\d+)\\)");
        @Override
        public Element findElement(Element element, String expression) {
            String[] expressions = expression.split("|");
            for (String s : expressions) {
                if (isElementTag(s) && onePattern.matcher(s).find()) {
                    element = element.select(onePattern.matcher(s).group(1)).first();
                } else if (isElementTag(s) && mutiPattern.matcher(s).find()) {
                    element = element.select(mutiPattern.matcher(s).group(1)).get(Integer.parseInt(mutiPattern.matcher(s).group(2)));
                } else {
                    element = element.select(s).first();
                }
            }
            return null;
        }

        private boolean isElementTag(String s) {
            return !(StringUtils.startsWith(s, ".") || StringUtils.startsWith(s, "#"));
        }
    }
    ;

    public Element findElement(Element element, String expression) {
        throw new IllegalAccessError("not implement");
    }

    protected Element findElement(Element element) {
        assert element != null;

        Elements children = element.children();
        if (children.size() < 1) return element;
        else return findElement(children.get(0));
    }

}
