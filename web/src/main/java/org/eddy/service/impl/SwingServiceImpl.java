package org.eddy.service.impl;

import org.eddy.dao.mapper.swing.StockRuleSwingMapper;
import org.eddy.service.SwingService;
import org.eddy.swing.entity.Swing;
import org.eddy.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by eddy on 2017/1/10.
 */
@Service
public class SwingServiceImpl implements SwingService{

    @Autowired
    private StockRuleSwingMapper ruleSwingMapper;

    @Override
    public void insert(List<Swing> lists) {
        Assert.notEmpty(lists);
        lists.stream().forEach(s -> CheckUtil.notBlank(s));
        ruleSwingMapper.insert(lists);
    }
}
