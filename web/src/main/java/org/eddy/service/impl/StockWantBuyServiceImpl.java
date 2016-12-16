package org.eddy.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.eddy.dao.mapper.stock.StockWantBuyMapper;
import org.eddy.entity.StockWantBuy;
import org.eddy.service.StockWantBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by eddy on 16/12/16.
 */
@Service
public class StockWantBuyServiceImpl implements StockWantBuyService {

    @Autowired
    private StockWantBuyMapper stockWantBuyMapper;

    @Override
    @Transactional
    public boolean insertOrUpdateNeedNotify(StockWantBuy stockWantBuy) {
        List<StockWantBuy> stockWantBuys = stockWantBuyMapper.selectByNameAndCode(stockWantBuy.getName(), stockWantBuy.getStockCode());
        if (CollectionUtils.isEmpty(stockWantBuys)) {
            stockWantBuyMapper.insert(stockWantBuy);
            return true;
        }
        StockWantBuy stockW = stockWantBuys.get(0);
        if (stockW.getUpdateTime().getTime() - System.currentTimeMillis() >= 60 * 60 * 1_000) {
            stockWantBuyMapper.updateById(stockW.getId(), new Date(), stockWantBuy.getCurrentPrice(), stockWantBuy.getCurrentUp());
            return true;
        }
        return false;
    }
}
