package org.eddy.swing;

import org.junit.Test;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by eddy on 2016/12/14.
 */
public class OTest {

    @Test
    public void test1() {
        SortedMap<String, String> sortedMap = new TreeMap<String, String>();
        sortedMap.put("a", "a");
        sortedMap.put("c", "b");
        sortedMap.put("d", "d");
        System.out.println(sortedMap.lastKey());
    }
}
