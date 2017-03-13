package org.eddy.swing.entity.groovy;

import org.eddy.entity.Stock;
import org.eddy.swing.entity.Validater;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.SortedMap;

/**
 *  价格上涨百分比大于一定比例
 * Created by Justice-love on 2017/3/13.
 */
public class RisePercent extends Validater {

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
        int percent = Integer.parseInt(arr[1]);
        if (stockList.size() < time) return false;
        for (int i = 0; i < time; i++) {
            Stock stock = stockList.get(i);
            //存在一次涨幅小于或等于期望的值，则不符合
            if ((Double.parseDouble(stock.getUpPrice()) / Double.parseDouble(stock.getYesterdayEnd())) * 100 <= percent) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "价格上涨百分比大于一定比例";
    }

    @Override
    public String name() {
        return "RisePercent";
    }
}
