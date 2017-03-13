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
public class SaleContentPercent extends Validater{

    @PostConstruct
    public void init() {
        register(name());
    }

    @Override
    public boolean validate(SortedMap<String, List<Stock>> groupStocks, String expression, String expect) {
        Assert.notEmpty(groupStocks, "groupStocks can not be empty");
        List<Stock> stockList = groupStocks.get(groupStocks.lastKey());
        String[] arr = expression.trim().split(CHAR);
        Assert.isTrue(arr.length == 2, "error expression: " + expression);
        int time = Integer.parseInt(arr[0]);
        double percent = Double.parseDouble(arr[1]);
        //adjust
        if (stockList.size() < time) return false;
        for (int i = 0; i < time; i++) {
            Stock stock = stockList.get(i);
            int buyCount = Integer.parseInt(stock.getBuy1()) + Integer.parseInt(stock.getBuy2()) + Integer.parseInt(stock.getBuy3());
            int saleCount = Integer.parseInt(stock.getSale1()) + Integer.parseInt(stock.getSale1()) + Integer.parseInt(stock.getSale1());
            //买入大于卖出
            if (buyCount * (1 + percent) > saleCount) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "卖出数目高于买入数目";
    }

    @Override
    public String name() {
        return "saleContentPercent";
    }
}
