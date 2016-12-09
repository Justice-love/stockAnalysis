package org.eddy;

import org.apache.commons.lang3.StringUtils;
import org.eddy.entity.Stock;
import org.eddy.entity.Url;
import org.eddy.entity.provider.StringTypeProvider;
import org.eddy.entity.provider.define.TypeProvider;
import org.eddy.exception.JsoupException;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Created by eddy on 2016/12/9.
 */
public abstract class ParseJob {
    public abstract Stock crawlPage(Url url) throws JsoupException;

    public void writePropertie(Stock stock, String key, String value) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Stock.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        PropertyDescriptor propertyDescriptor = findPropertyDescriptor(propertyDescriptors, key);
        TypeProvider provider = findTypeProvider(propertyDescriptor);
        propertyDescriptor.getWriteMethod().invoke(stock, provider.convert(value));
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
