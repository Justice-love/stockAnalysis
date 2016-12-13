package org.eddy.swing;

import org.eddy.swing.entity.Swing;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by eddy on 2016/12/13.
 */
public class SwingTest {

    @Test
    public void test() {
        Map<String, List<Swing>> listMap =  SwingContext.getContext().getSwings();
        Assert.assertEquals(2, listMap.size());
        Assert.assertEquals(1, listMap.get("a").size());
        Assert.assertNotNull(listMap.get("a").get(0).getChild());
        Assert.assertEquals(1, listMap.get("d").size());
        Assert.assertNull(listMap.get("d").get(0).getChild());
    }
}
