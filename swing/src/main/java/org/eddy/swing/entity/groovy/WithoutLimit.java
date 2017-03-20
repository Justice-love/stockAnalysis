package org.eddy.swing.entity.groovy;

import org.eddy.entity.Stock;
import org.eddy.swing.entity.Validater;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.SortedMap;

/**
 * Created by eddy on 2017/3/21.
 */
@Component
public class WithoutLimit extends Validater {

    @PostConstruct
    public void init() {
        register(name());
    }

    @Override
    public boolean validate(SortedMap<String, List<Stock>> groupStocks, String expression, String expect) {
        Assert.notEmpty(groupStocks, "groupStocks can not be empty");
        List<Stock> stockList = groupStocks.get(groupStocks.lastKey());
        if (stockList.size() < 1) return false;
        Stock stock = stockList.get(0);
        if ((Double.parseDouble(stock.getUpPrice()) / (Double.parseDouble(stock.getYesterdayEnd()) == 0.0 ? 1 : Double.parseDouble(stock.getYesterdayEnd()))) * 100 >= 9.8) {
            return false;
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "过滤涨停";
    }

    @Override
    public String name() {
        return "withoutLimit";
    }
}
