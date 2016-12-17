package org.eddy.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by eddy on 2016/12/11.
 */
public class OTest {

    ThreadLocal threadLocal = new ThreadLocal<String>();

    @Test
    public void test() {
        Assert.assertNotNull(Arrays.asList().stream().collect(Collectors.toList()));
    }

    @Test
    public void test1() {
        threadLocal.set("a");
        threadLocal.set("b");
        System.out.println(threadLocal.get());
    }

}
