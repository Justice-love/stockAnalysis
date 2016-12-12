package org.eddy.service.impl;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.eddy.dao.mapper.stock.ErrorStockMapper;
import org.eddy.dao.mapper.stock.StockMapper;
import org.eddy.entity.Stock;
import org.eddy.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by eddy on 2016/12/11.
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private ErrorStockMapper errorStockMapper;

    @Override
    @Transactional
    public void loadStockPerMin(List<Stock> list) {
        Map<Boolean, List<Stock>> result = Optional.ofNullable(list).orElse(new ArrayList<Stock>()).stream().collect(Collectors.groupingBy(s -> s.isHasError()));
        //正常数据插入
        Optional.ofNullable(Lists.partition(null == result.get(Stock.NO_ERROR) ? Arrays.asList() : result.get(Stock.NO_ERROR), 500)).orElse(Arrays.asList()).stream().forEach(stocks -> {if (null != stocks && !stocks.isEmpty()) stockMapper.insert(stocks);});
        //插入错误数据
        Optional.ofNullable(Lists.partition(null == result.get(Stock.HAS_ERROR) ? Arrays.asList() : result.get(Stock.HAS_ERROR), 500)).orElse(Arrays.asList()).stream().forEach(stocks -> {if (null != stocks && !stocks.isEmpty()) errorStockMapper.insert(stocks);});
    }

    @Override
    public boolean isNeedLoad(Stock stock) {
        if (null == stock) return false;
        if (stock.isHasError()) {
            //数据为空则需要加载该条数据
            return Optional.ofNullable(errorStockMapper.countByCode(stock.getStockCode())).orElse(0).intValue() == 0;
        } else {
            return Optional.ofNullable(stockMapper.countByNameDateAndTime(stock.getStockCode(), stock.getDate(), stock.getTime())).orElse(0).intValue() == 0;
        }
    }

    @Override
    @Transactional
    public List<Stock> computerDailyStocks() {
        List<String> dates = stockMapper.groupByDate();
        String maxDate = stockMapper.selectMaxDate();
        return dates.stream().flatMap(s -> {
            List<Stock> ori = stockMapper.selectLastStockOneDay(s);
            List<Stock> statistic = stockMapper.selectStatisticStock(s);
            merge(ori, statistic);
            stockMapper.deleteByDate(s);
            return  ori.stream();
        }).collect(Collectors.toList());
    }

    private void merge(List<Stock> ori, List<Stock> statistic) {
        ori.stream().forEach(s -> {
            Stock stock = statistic.stream().filter(t -> StringUtils.equals(s.getStockCode(), t.getStockCode())).findFirst().get();
            s.setBuy1(stock.getBuy1());
            s.setBuy2(stock.getBuy2());
            s.setBuy3(stock.getBuy3());
            s.setSale1(stock.getSale1());
            s.setSale2(stock.getSale2());
            s.setSale3(stock.getSale3());
        });
    }
}
