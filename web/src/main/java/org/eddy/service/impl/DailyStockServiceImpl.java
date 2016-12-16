package org.eddy.service.impl;

import com.google.common.collect.Lists;
import org.eddy.dao.mapper.stock.DailyStockMapper;
import org.eddy.entity.Stock;
import org.eddy.service.DailyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by eddy on 2016/12/12.
 */
@Service
public class DailyStockServiceImpl implements DailyStockService {

    @Autowired
    private DailyStockMapper dailyStockMapper;

    @Override
    @Transactional
    public void loadDailyStock(List<Stock> source) {
        Lists.partition(Optional.ofNullable(source).orElse(Arrays.asList()), 500).stream().forEach(stocks -> {
            dailyStockMapper.insert(stocks);
        });
    }

    @Override
    public List<Stock> selectSortedStocks(String code) {
        return dailyStockMapper.selectSortedStocks(code);
    }
}
