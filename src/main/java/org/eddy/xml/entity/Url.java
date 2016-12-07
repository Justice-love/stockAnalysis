package org.eddy.xml.entity;

import org.eddy.entity.SelectType;

import java.util.List;

/**
 * Created by eddy on 2016/12/6.
 */
public class Url {

    public static final String URL_TAG_NAME = "url";
    public static final String RULE_TAG_NAME = "urlRule";

    //REQUIRED
    private String url;
    private List<UrlRule> urlRuleList;

    //IMPLIED
    private String pathVariableClass;

    public Url(String url) {
        this.url = url;
    }

    public Url() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<UrlRule> getUrlRuleList() {
        return urlRuleList;
    }

    public void setUrlRuleList(List<UrlRule> urlRuleList) {
        this.urlRuleList = urlRuleList;
    }

    public String getPathVariableClass() {
        return pathVariableClass;
    }

    public void setPathVariableClass(String pathVariableClass) {
        this.pathVariableClass = pathVariableClass;
    }

    public static class UrlRule {
        private SelectType selectType;
        private String value;
        private String property;

        public UrlRule(SelectType selectType, String value, String property) {
            this.selectType = selectType;
            this.value = value;
            this.property = property;
        }

        public UrlRule() {
        }

        public SelectType getSelectType() {
            return selectType;
        }

        public void setSelectType(SelectType selectType) {
            this.selectType = selectType;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
    }
}