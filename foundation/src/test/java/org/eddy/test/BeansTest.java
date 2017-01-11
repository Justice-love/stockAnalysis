package org.eddy.test;

import org.eddy.entity.pojo.User;
import org.eddy.entity.provider.define.TypeProvider;
import org.eddy.util.BeansUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by eddy on 17/1/11.
 */
public class BeansTest {

    @Test
    public void test() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        User user = new User();
        user.setName("eddy");
        TypeProvider typeProvider =  BeansUtil.readPropertieWithType(user, "name");
        Assert.assertEquals("eddy", typeProvider.get());
    }

    @Test
    public void test2() throws Exception {
        User user = new User();
        user.setName("eddy");
        String value =  BeansUtil.readPropertie4String(user, "name");
        Assert.assertEquals("eddy", value);
    }
}
