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
    /**
     * 买入价格持续高于当前价格
     */
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
    /**
     * 卖出价格持续高于当前价格
     */
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
                //当前价格 > 希望卖出的价格 抛售
                if (Double.parseDouble(stock.getPrice()) > Double.parseDouble(stock.getToSalePrice())) {
                    return false;
                }
            }
            return true;
        }
    },
    /**
     * 买入持续比卖出高于一定比例
     */
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
                //卖出大于买入
                if (saleCount * (1 + precent) > buyCount ) {
                    return false;
                }
            }
            return true;
        }
    },
    /**
     * 卖出持续比买入高于一定比例
     */
    saleContentPercent {
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
                if (buyCount * (1 + precent) >= saleCount) {
                    return false;
                }
            }
            return true;
        }
    },
    /**
     * 持续涨幅
     */
    continuedUp {
        @Override
        public boolean validate(SortedMap<String, List<Stock>> groupStocks, String expression, String expect) {
            Assert.notEmpty(groupStocks, "groupStocks can not be empty");
            String[] arr = expression.trim().split(CHAR);
            Assert.isTrue(arr.length == 2, "error expression: " + expression);
            int time = Integer.parseInt(arr[0]);
            double precent = Double.parseDouble(arr[1]);
            Assert.isTrue(precent >= 0, "percent must >= 0");
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
                //有一次希望的幅度大于实际的幅度，则不符合规则
                if (precent > (Double.parseDouble(stock.getPrice()) - Double.parseDouble(stock.getYesterdayEnd())) / Double.parseDouble(stock.getYesterdayEnd())) {
                    return false;
                }
            }
            return true;
        }
    },

    /**
     * 持续下跌超过一定幅度
     */
    continueDown {
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
                double p = (Double.parseDouble(stock.getPrice()) - Double.parseDouble(stock.getYesterdayEnd())) / Double.parseDouble(stock.getYesterdayEnd());
                //有一次涨了或者希望的幅度大于实际的幅度，则不符合规则
                if (p > 0 || Math.abs(precent) > Math.abs(p)) {
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
