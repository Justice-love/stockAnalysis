package org.eddy.swing.entity.groovy;

import org.eddy.entity.Stock;
import org.eddy.swing.entity.Validater;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.SortedMap;

/**
 * 卖出价格持续高于当前价格
 * expression：5 (当天倒序持续多少次)
 * Created by eddy on 2016/12/28.
 */
@Component
public class SalePrice extends Validater{


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
            //当前价格 > 希望卖出的价格 抛售
            if (Double.parseDouble(stock.getPrice()) > Double.parseDouble(stock.getToSalePrice())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "卖出价格持续高于当前价格";
    }

    @Override
    public String name() {
        return "salePrice";
    }
}
