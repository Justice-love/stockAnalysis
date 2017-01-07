package org.eddy.service.impl;

import org.eddy.dao.mapper.swing.SwingScriptMapper;
import org.eddy.service.SwingScriptService;
import org.eddy.swing.entity.ValidateExecuter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eddy on 2017/1/7.
 */
@Service
public class SwingScriptServiceImpl implements SwingScriptService{

    @Autowired
    private SwingScriptMapper scriptMapper;

    @Override
    public void insert(ValidateExecuter executer) {
        scriptMapper.insert(executer);
    }

    @Override
    public List<ValidateExecuter> selectAll() {
        return scriptMapper.selectAll();
    }
}
