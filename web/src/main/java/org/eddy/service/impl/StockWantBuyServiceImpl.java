package org.eddy.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.eddy.dao.mapper.stock.StockWantBuyMapper;
import org.eddy.entity.BoughtStock;
import org.eddy.entity.StockWantBuy;
import org.eddy.service.BoughtStockService;
import org.eddy.service.StockWantBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by eddy on 16/12/16.
 */
@Service
public class StockWantBuyServiceImpl implements StockWantBuyService {

    @Autowired
    private StockWantBuyMapper stockWantBuyMapper;

    @Autowired
    private BoughtStockService boughtStockService;

    @Override
    @Transactional
    public boolean insertOrUpdateNeedNotify(StockWantBuy stockWantBuy) {
        Assert.notNull(stockWantBuy);
        List<BoughtStock> boughtStocks = boughtStockService.selectByCode(stockWantBuy.getStockCode());
        if (CollectionUtils.isNotEmpty(boughtStocks)) {
            return false;
        }
        List<StockWantBuy> stockWantBuys = stockWantBuyMapper.selectByNameAndCode(stockWantBuy.getName(), stockWantBuy.getStockCode());
        if (CollectionUtils.isEmpty(stockWantBuys)) {
            stockWantBuyMapper.insert(stockWantBuy);
            return true;
        }
        StockWantBuy stockW = stockWantBuys.get(0);
        if (System.currentTimeMillis() - stockW.getUpdateTime().getTime() >= 60 * 60 * 1_000) {
            stockWantBuyMapper.updateById(stockW.getId(), new Date(), stockWantBuy);
            return true;
        }
        return false;
    }

    @Override
    public List<StockWantBuy> selectAllToBuy() {
        return stockWantBuyMapper.selectAll();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        stockWantBuyMapper.deleteById(id);
    }

    @Override
    public StockWantBuy selectById(int id) {
        Assert.isTrue(id > 0);
        return stockWantBuyMapper.selectById(id);
    }
}
