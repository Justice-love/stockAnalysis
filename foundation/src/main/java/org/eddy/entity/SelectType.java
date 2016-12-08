package org.eddy.entity;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;

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
        @Override
        public Element findElement(Element element, String expression) {
            String[] expressions = expression.split("|");
            Arrays.stream(expressions).forEach(s -> {
                if (isElementTag(s)) {
                }
            });
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
