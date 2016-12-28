package org.eddy.swing.entity.groovy;

import org.eddy.entity.Stock;
import org.eddy.swing.entity.Validater;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.SortedMap;

/**
 * Created by eddy on 2016/12/28.
 */
@Component
public class HigherThanExpected extends Validater{

    @PostConstruct
    public void init() {
        register(name());
    }

    @Override
    public boolean validate(SortedMap<String, List<Stock>> groupStocks, String expression, String expect) {
        Assert.notEmpty(groupStocks, "groupStocks can not be empty");
        List<Stock> stockList = groupStocks.get(groupStocks.lastKey());
        int time = Integer.parseInt(expression.trim());
        //adjust
        if (stockList.size() < time) return false;
        for (int i = 0; i < time; i++) {
            Stock stock = stockList.get(i);
            //当前价格 > 想要买入的价格， 跌落
            if (Double.parseDouble(stock.getPrice()) < Double.parseDouble(expect)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "价格高于预期，存在风险";
    }

    @Override
    public String name() {
        return "higherThanExpected";
    }
}
