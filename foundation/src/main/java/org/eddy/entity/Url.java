package org.eddy.entity;

import org.apache.commons.lang3.StringUtils;
import org.eddy.entity.SelectType;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Created by eddy on 2016/12/6.
 */
public class Url {

    public static final String URL_TAG_NAME = "url";
    public static final String RULE_TAG_NAME = "urlRule";
    public static final String JSOUP_TYPE = "jsoup";
    public static final String HTTPCLIENT_TYPE = "httpClient";

    //REQUIRED
    private String url;
    private Test test;
    private List<UrlRule> urlRuleList;
    private String type;

    //IMPLIED
    private String pathVariableClass;

    //funciton
    public Url copy(String u) {
        Url url = new Url();
        url.setType(this.getType());
        url.setTest(this.getTest());
        url.setUrl(u);
        url.setUrlRuleList(this.getUrlRuleList());
        return url;
    }

    public Url(String url) {
        this.url = url;
    }

    public Url() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public static class UrlRule {
        private SelectType selectType;
        private String expression;
        private String property;
        private Boolean skipTest;

        public UrlRule(SelectType selectType, String expression, String property, Boolean skipTest) {
            this.selectType = selectType;
            this.expression = expression;
            this.property = property;
            this.skipTest = skipTest;
        }

        public UrlRule() {
        }

        public SelectType getSelectType() {
            return selectType;
        }

        public void setSelectType(SelectType selectType) {
            this.selectType = selectType;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public Boolean getSkipTest() {
            return skipTest;
        }

        public void setSkipTest(Boolean skipTest) {
            this.skipTest = skipTest;
        }
    }

    public static class Test{
        private SelectType selectType;
        private String value;
        private String validate;

        public Test(SelectType selectType, String value, String validate) {
            this.selectType = selectType;
            this.value = value;
            this.validate = validate;
        }

        public Test() {
        }

        private SelectType getSelectType() {
            return selectType;
        }

        public void setSelectType(SelectType selectType) {
            this.selectType = selectType;
        }

        private String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        private String getValidate() {
            return validate;
        }

        public void setValidate(String validate) {
            this.validate = validate;
        }

        public boolean test(Object content, Url url) {
            if (StringUtils.equals(Url.JSOUP_TYPE, url.getType())) {
                Element element = (Element) content;
                String end = this.getSelectType().findElement(element, this.getValue());
                return end != null && StringUtils.equals(this.getValidate(), end);
            } else if (StringUtils.equals(Url.HTTPCLIENT_TYPE, url.getType())) {
                return Boolean.parseBoolean(this.getSelectType().findElement(content, null));
            } else {
                return false;
            }
        }
    }
}