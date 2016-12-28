package org.eddy.swing.entity.groovy;

import org.eddy.entity.Stock;
import org.eddy.swing.entity.Validater;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * 持续涨幅
 * Created by eddy on 2016/12/28.
 */
@Component
public class ContinuedUp extends Validater{

    @PostConstruct
    public void init() {
        register(name());
    }

    @Override
    public boolean validate(SortedMap<String, List<Stock>> groupStocks, String expression, String expect) {
        Assert.notEmpty(groupStocks, "groupStocks can not be empty");
        String[] arr = expression.trim().split(CHAR);
        Assert.isTrue(arr.length == 2, "error expression: " + expression);
        int time = Integer.parseInt(arr[0]);
        double precent = Double.parseDouble(arr[1]) * 0.01;
        SortedMap<String, List<Stock>> headMap = groupStocks.headMap(groupStocks.lastKey());
        Assert.isTrue(headMap.size() >= time, "history data not enough");
        Map.Entry<String, List<Stock>>[] entries = (Map.Entry<String, List<Stock>>[]) headMap.entrySet().toArray(new Map.Entry[1]);
        for (int i= 1; i <= time; i++) {
            List<Stock> history = history = entries[entries.length - 1 - (i - 1)].getValue();
            Assert.notEmpty(history, "history size is empty");
            Stock stock = history.get(0);
            //昨天数据为0
            if (Double.parseDouble(stock.getYesterdayEnd()) == 0) {
                continue;
            }
            //有一次希望的幅度大于实际的幅度，则不符合规则
            if (precent > (Double.parseDouble(stock.getPrice()) - Double.parseDouble(stock.getYesterdayEnd())) / Double.parseDouble(stock.getYesterdayEnd())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "持续上涨";
    }

    @Override
    public String name() {
        return "continuedUp";
    }
}
