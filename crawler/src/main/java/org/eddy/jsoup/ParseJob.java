package org.eddy.jsoup;

import org.apache.commons.lang3.StringUtils;
import org.eddy.entity.Stock;
import org.eddy.entity.Url;
import org.eddy.entity.provider.StringTypeProvider;
import org.eddy.entity.provider.define.TypeProvider;
import org.eddy.exception.JsoupException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eddy on 2016/12/8.
 */
public class ParseJob {

    public Stock crawlPage(Url url) throws JsoupException{
        try {
            assert url != null;
            Document document = Jsoup.connect(url.getUrl()).get();
            if (!url.getTest().test(document)) {
                return null;
            }
            Stock result = new Stock();

            BeanInfo beanInfo = Introspector.getBeanInfo(Stock.class);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            List<Url.UrlRule> ruleList =  url.getUrlRuleList();
            ruleList.stream().forEach(r -> {
                Element element = r.getSelectType().findElement(document, r.getExpression());
                PropertyDescriptor propertyDescriptor = findPropertyDescriptor(propertyDescriptors, r.getProperty());
                TypeProvider provider = findTypeProvider(propertyDescriptor);
                try {
                    propertyDescriptor.getWriteMethod().invoke(result, provider.convert(element.text()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            return result;
        } catch (IOException e) {
            throw new JsoupException(e);
        } catch (IntrospectionException e) {
            throw new JsoupException(e);
        } catch (Exception e) {
            throw new JsoupException(e);
        }
    }

    private PropertyDescriptor findPropertyDescriptor(PropertyDescriptor[] propertyDescriptors, String name) {
        return Arrays.stream(propertyDescriptors).filter(s -> StringUtils.equals(s.getDisplayName(), name)).findFirst().get();
    }

    private TypeProvider findTypeProvider(PropertyDescriptor propertyDescriptor) {
        if (propertyDescriptor.getPropertyType() == String.class) {
            return new StringTypeProvider();
        } else {
            return new StringTypeProvider();
        }
    }
}
