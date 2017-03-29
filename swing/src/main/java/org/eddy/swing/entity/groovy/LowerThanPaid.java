package org.eddy.swing.entity.groovy;

import org.eddy.entity.Stock;
import org.eddy.swing.entity.Validater;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.SortedMap;

/**
 * 低于付款价格
 * expect: {boughtPrice} (Stock 字段) 或者 3.8 期望的价格
 * Created by eddy on 2016/12/28.
 */
@Component
public class LowerThanPaid extends Validater{

    @PostConstruct
    public void init() {
        register(name());
    }

    @Override
    public boolean validate(SortedMap<String, List<Stock>> groupStocks, String expression, String expect) {
        Assert.notEmpty(groupStocks, "groupStocks can not be empty");
        List<Stock> stockList = groupStocks.get(groupStocks.lastKey());
        Assert.notEmpty(stockList);
        Stock last = stockList.get(0);
        if (Double.parseDouble(last.getPrice()) < Double.parseDouble(expect)) {
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "价格低于购买价";
    }

    @Override
    public String name() {
        return "lowerThanPaid";
    }
}
