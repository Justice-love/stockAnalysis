package com.eddy.entity;

import org.jsoup.nodes.Element;

/**
 * Created by admin on 2016/12/4.
 */
public class JsoupParam {

    private SelectType selectType;
    private String expression;
    private Element element;

    public JsoupParam(SelectType selectType, String expression, Element element) {
        this.selectType = selectType;
        this.expression = expression;
        this.element = element;
    }

    public SelectType getSelectType() {
        return selectType;
    }

    public void setSelectType(SelectType selectType) {
        this.selectType = selectType;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
