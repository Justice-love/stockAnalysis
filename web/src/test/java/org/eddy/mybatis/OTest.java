package org.eddy.mybatis;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by eddy on 2016/12/11.
 */
public class OTest {

    @Test
    public void test() {
        Assert.assertNotNull(Arrays.asList().stream().collect(Collectors.toList()));
    }
}
