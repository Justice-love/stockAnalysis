package org.eddy.service.impl;

import org.eddy.dao.mapper.stock.BoughtStockMapper;
import org.eddy.entity.BoughtStock;
import org.eddy.service.BoughtStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by eddy on 2016/12/17.
 */
@Service
public class BoughtStockServiceImpl implements BoughtStockService {

    @Autowired
    private BoughtStockMapper boughtStockMapper;

    @Override
    @Transactional
    public void insertOne(BoughtStock boughtStock) {
        Assert.notNull(boughtStock);
        boughtStockMapper.insertOne(boughtStock);
    }

    @Override
    @Transactional
    public void deleteOne(int id) {
        Assert.isTrue(id > 0);
        boughtStockMapper.deleteOne(id);
    }

    @Override
    public List<BoughtStock> selectAll() {
        return boughtStockMapper.selectBought();
    }

    @Override
    public List<BoughtStock> selectByCode(String code) {
        Assert.notNull(code);
        return boughtStockMapper.selectByCode(code);
    }
}
