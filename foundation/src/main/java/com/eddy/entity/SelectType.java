package com.eddy.entity;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
            Elements cssElements = element.getElementsByClass(expression);
            return findElement(cssElements.get(0));
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
