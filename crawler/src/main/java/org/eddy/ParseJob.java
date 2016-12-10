package org.eddy;

import org.eddy.entity.Stock;
import org.eddy.entity.Url;
import org.eddy.entity.provider.define.TypeProvider;
import org.eddy.exception.JsoupException;
import org.eddy.util.BeansUtil;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by eddy on 2016/12/9.
 */
public abstract class ParseJob {
    public abstract Stock crawlPage(Url url) throws JsoupException;

    public void writePropertie(Stock stock, String key, String value) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor propertyDescriptor = BeansUtil.findPropertyDescriptor(Stock.class, key);
        TypeProvider provider = BeansUtil.findTypeProvider(propertyDescriptor);
        propertyDescriptor.getWriteMethod().invoke(stock, provider.convert(value));
    }

}
