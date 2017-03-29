package org.eddy.swing.entity.groovy;

import org.eddy.entity.Stock;
import org.eddy.swing.entity.Validater;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.SortedMap;

/**
 * 买入价格持续高于当前价格
 * 传入expression， 格式如：5 (当天按时间倒序次数)
 * Created by eddy on 2016/12/28.
 */
@Component
public class BuyPrice extends Validater{

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
            if (Double.parseDouble(stock.getPrice()) > Double.parseDouble(stock.getToBuyPrice())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "买入价格高于当前价格";
    }

    @Override
    public String name() {
        return "buyPrice";
    }
}
