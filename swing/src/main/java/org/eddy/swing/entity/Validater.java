package org.eddy.swing.entity;

import org.eddy.entity.Stock;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * 数据按照时间，日期倒序排列
 * Created by eddy on 2016/12/13.
 */
public enum Validater {
    buyPrice {
        @Override
        public boolean validate(SortedMap<String, List<Stock>> groupStocks, String expression, String expect) {
            Assert.notEmpty(groupStocks, "groupStocks can not be empty");
            List<Stock> stockList = groupStocks.get(groupStocks.lastKey());
            int time = Integer.parseInt(expression.trim());
            //adjust
            if (stockList.size() < time) time = stockList.size();
            for (int i = 0; i < time; i++) {
                Stock stock = stockList.get(i);
                //当前价格 > 想要买入的价格， 跌落
                if (Double.parseDouble(stock.getPrice()) > Double.parseDouble(stock.getToBuyPrice())) {
                    return false;
                }
            }
            return true;
        }
    },
    salePrice {
        @Override
        public boolean validate(SortedMap<String, List<Stock>> groupStocks, String expression, String expect) {
            Assert.notEmpty(groupStocks, "groupStocks can not be empty");
            List<Stock> stockList = groupStocks.get(groupStocks.lastKey());
            int time = Integer.parseInt(expression.trim());
            //adjust
            if (stockList.size() < time) time = stockList.size();
            for (int i = 0; i < time; i++) {
                Stock stock = stockList.get(i);
                //想要卖出的价格 > 当前价格, 看涨
                if (Double.parseDouble(stock.getToSalePrice()) > Double.parseDouble(stock.getPrice())) {
                    return true;
                }
            }
            return false;
        }
    },
    buyCountPercent {
        @Override
        public boolean validate(SortedMap<String, List<Stock>> groupStocks, String expression, String expect) {
            Assert.notEmpty(groupStocks, "groupStocks can not be empty");
            List<Stock> stockList = groupStocks.get(groupStocks.lastKey());
            String[] arr = expression.trim().split(CHAR);
            Assert.isTrue(arr.length == 2, "error expression: " + expression);
            int time = Integer.parseInt(arr[0]);
            double precent = Double.parseDouble(arr[1]);
            //adjust
            if (stockList.size() < time) time = stockList.size();
            for (int i = 0; i < time; i++) {
                Stock stock = stockList.get(i);
                int buyCount = Integer.parseInt(stock.getBuy1()) + Integer.parseInt(stock.getBuy2()) + Integer.parseInt(stock.getBuy3());
                int saleCount = Integer.parseInt(stock.getSale1()) + Integer.parseInt(stock.getSale1()) + Integer.parseInt(stock.getSale1());
                //买入大于卖出
                if (buyCount >= saleCount * (1 + precent)) {
                    return true;
                }
            }
            return false;
        }
    },
    continuedUp {
        @Override
        public boolean validate(SortedMap<String, List<Stock>> groupStocks, String expression, String expect) {
            Assert.notEmpty(groupStocks, "groupStocks can not be empty");
            String[] arr = expression.trim().split(CHAR);
            Assert.isTrue(arr.length == 2, "error expression: " + expression);
            int time = Integer.parseInt(arr[0]);
            double precent = Double.parseDouble(arr[1]);
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
                //要求的比例 > 历史某一天的价格涨幅
                if (precent > (Double.parseDouble(stock.getPrice()) - Double.parseDouble(stock.getYesterdayEnd())) / Double.parseDouble(stock.getYesterdayEnd())) {
                    return false;
                }
            }
            return true;
        }
    }
    ;
    protected static String CHAR = "#";
    public boolean validate(SortedMap<String, List<Stock>> groupStocks, String expression, String expect) {
        throw new UnsupportedOperationException("not support");
    }
}
