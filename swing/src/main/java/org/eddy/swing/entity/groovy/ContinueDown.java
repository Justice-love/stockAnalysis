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
 * 数据按照时间，日期倒序排列
 * 传入expression， 格式如：5#3 (日期排除当天倒序，取每天最后一条#百分比)
 * Created by eddy on 2016/12/28.
 */
@Component
public class ContinueDown extends Validater{

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
        Map.Entry<String, List<Stock>>[] entries = (Map.Entry<String, List<Stock>>[]) headMap.entrySet().toArray();
        for (int i= 1; i <= time; i++) {
            List<Stock> history = entries[entries.length - 1 - i- 1].getValue();
            Assert.notEmpty(history, "history size is empty");
            Stock stock = history.get(0);
            //昨天数据为0
            if (Double.parseDouble(stock.getYesterdayEnd()) == 0) {
                continue;
            }
            double p = (Double.parseDouble(stock.getPrice()) - Double.parseDouble(stock.getYesterdayEnd())) / Double.parseDouble(stock.getYesterdayEnd());
            //有一次涨了或者希望的幅度大于实际的幅度，则不符合规则
            if (p > precent) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "持续下跌";
    }

    @Override
    public String name() {
        return "continueDown";
    }
}
