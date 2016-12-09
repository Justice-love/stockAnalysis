package org.eddy;

import org.eddy.entity.Stock;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by eddy on 2016/12/8.
 */
public class BeansTest {

    @Test
    public void test1() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Stock.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Arrays.stream(propertyDescriptors).forEach( p -> {
            System.out.println(p.getDisplayName());
            System.out.println(p.getPropertyEditorClass());
            System.out.println(p.getPropertyType());
            System.out.println(p.getName());
        });
    }

    @Test
    public void test2() {
        try {
            Arrays.asList(new String[]{"1"}).stream().anyMatch(s -> {
                if ("1".endsWith(s)) {
                    throw new RuntimeException("a");
                }
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
