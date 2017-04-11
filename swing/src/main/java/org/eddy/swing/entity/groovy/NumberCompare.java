package org.eddy.swing.entity.groovy;

import org.eddy.entity.Stock;
import org.eddy.swing.entity.Validater;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.SortedMap;

/**
 * 数目比较
 * Created by eddy on 2017/4/11.
 */
@Component
public class NumberCompare extends Validater {

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
        return "买入卖出数目比较";
    }

    @Override
    public String name() {
        return "numberCompare";
    }
}
