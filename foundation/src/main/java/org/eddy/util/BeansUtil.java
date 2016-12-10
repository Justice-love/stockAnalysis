package org.eddy.util;

import org.apache.commons.lang3.StringUtils;
import org.eddy.entity.Stock;
import org.eddy.entity.provider.StringTypeProvider;
import org.eddy.entity.provider.define.TypeProvider;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Created by eddy on 2016/12/10.
 */
public class BeansUtil {

    public static PropertyDescriptor findPropertyDescriptor(Class glass, String name) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(glass);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        return Arrays.stream(propertyDescriptors).filter(s -> StringUtils.equals(s.getDisplayName(), name)).findFirst().get();
    }

    public static TypeProvider findTypeProvider(PropertyDescriptor propertyDescriptor) {
        if (propertyDescriptor.getPropertyType() == String.class) {
            return new StringTypeProvider();
        } else {
            return new StringTypeProvider();
        }
    }

    public static String readPropertie(Object obj, String key) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Stock.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        PropertyDescriptor propertyDescriptor = findPropertyDescriptor(obj.getClass(), key);
        Object value = propertyDescriptor.getReadMethod().invoke(obj);
        return value.toString();
    }
}
