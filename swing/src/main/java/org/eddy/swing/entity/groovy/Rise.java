package org.eddy.swing.entity.groovy;

import org.eddy.entity.Stock;
import org.eddy.swing.entity.Validater;

import java.util.List;
import java.util.SortedMap;

/**
 *  股票上涨大于一定比例
 * Created by Justice-love on 2017/3/13.
 */
public class Rise extends Validater {
    @Override
    public boolean validate(SortedMap<String, List<Stock>> groupStocks, String expression, String expect) {
        return false;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String name() {
        return null;
    }
}
