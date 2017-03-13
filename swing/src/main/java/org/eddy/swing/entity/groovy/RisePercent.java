package org.eddy.swing.entity.groovy;

import org.eddy.entity.Stock;
import org.eddy.swing.entity.Validater;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.SortedMap;

/**
 *  上涨大于一定比例
 * Created by Justice-love on 2017/3/13.
 */
public class RisePercent extends Validater {

    @PostConstruct
    public void init() {
        register(name());
    }
    @Override
    public boolean validate(SortedMap<String, List<Stock>> groupStocks, String expression, String expect) {
        return false;
    }

    @Override
    public String getDescription() {
        return "价格上涨百分比大于一定比例";
    }

    @Override
    public String name() {
        return "RisePercent";
    }
}
