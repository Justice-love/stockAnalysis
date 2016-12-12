package org.eddy.test;

import org.junit.Test;

import java.util.Optional;

/**
 * Created by eddy on 16/12/12.
 */
public class OTest {

    @Test
    public void test() {
        String str = "true";
//        System.out.println(Optional.ofNullable(str).orElse("d"));
        System.out.println(Optional.ofNullable(str).map(s -> Boolean.parseBoolean(s)).orElse(false));
    }
}
