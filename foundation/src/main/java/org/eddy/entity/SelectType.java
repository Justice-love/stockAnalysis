package org.eddy.entity;

import org.apache.commons.lang3.StringUtils;
import org.eddy.util.BeansUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
    },
    regx("httpClient") {
        Pattern pattern = Pattern.compile("\\w*\\s+\\w+=\".+\";[\\n]?");
        @Override
        public String findElement(Object content, String expression) {
            Matcher matcher = pattern.matcher(content.toString());
            return Boolean.toString(matcher.matches() && !content.toString().contains("FAILED") && content.toString().split(",").length >= 32 );
        }
    },
    index("httpClient") {
        @Override
        public String findElement(Object content, String expression) {
            String value = content.toString();
            String temp = value.substring(value.indexOf("\"") + 1);
            value = temp.substring(0, temp.indexOf("\""));
            String[] values = value.split(",");
            return values[Integer.valueOf(expression)];
        }
    },
    computer("httpClient") {
        @Override
        public String findElement(Object content, String expression) {
            Stock stock = (Stock) content;
            String[] expressions = expression.split("\\s");
            try {
                String first = BeansUtil.readPropertie4String(stock, expressions[0]);
                String second = BeansUtil.readPropertie4String(stock, expressions[2]);
                String computer =  expressions[1];
                switch (computer) {
                    case "+":
                        return Double.toString(Double.parseDouble(first) + Double.parseDouble(second));
                    case "-":
                        double result = Double.parseDouble(first) - Double.parseDouble(second);
                        DecimalFormat df = new DecimalFormat("0.00");
                        df.setRoundingMode(RoundingMode.HALF_UP);
                        return df.format(result);
                    case "*":
                        return Double.toString(Double.parseDouble(first) * Double.parseDouble(second));
                    case "/":
                        NumberFormat fmt = NumberFormat.getPercentInstance();
                        fmt.setMaximumFractionDigits(2);
                        return Double.parseDouble(second) == 0 ? fmt.format(Double.parseDouble(first)) : fmt.format(Double.parseDouble(first) / Double.parseDouble(second));
                    default:
                        return "";
                }
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
        }
    },
    parseRequest("httpClient") {
        @Override
        public String findElement(Object content, String expression) {
            String url = (String) content;
            String[] arr = expression.split("\\s");
            switch (arr[0]) {
                case "behind":
                    return url.split(arr[1])[1];
                default:
                    return "";
            }
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
