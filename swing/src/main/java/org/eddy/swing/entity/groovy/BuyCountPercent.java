package org.eddy.swing.entity.groovy;

import org.eddy.entity.Stock;
import org.eddy.swing.entity.Validater;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.SortedMap;

/**
 * 买入持续比卖出高于一定比例
 * 传入expression， 格式如：5#0.1 (当天按时间倒序次数#double 非百分比)
 * Created by eddy on 2016/12/28.
 */
@Component
public class BuyCountPercent extends Validater{

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
        double precent = Double.parseDouble(arr[1]);
        //adjust
        if (stockList.size() < time) return false;
        for (int i = 0; i < time; i++) {
            Stock stock = stockList.get(i);
            int buyCount = Integer.parseInt(stock.getBuy1()) + Integer.parseInt(stock.getBuy2()) + Integer.parseInt(stock.getBuy3());
            int saleCount = Integer.parseInt(stock.getSale1()) + Integer.parseInt(stock.getSale1()) + Integer.parseInt(stock.getSale1());
            //卖出大于买入
            double count = saleCount * (1 + precent);
            if (count > buyCount ) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "买入数目高于卖出数目";
    }

    @Override
    public String name() {
        return "buyCountPercent";
    }
}
