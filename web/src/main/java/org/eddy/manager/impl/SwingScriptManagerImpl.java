package org.eddy.manager.impl;

import org.eddy.manager.SwingScriptManager;
import org.eddy.service.SwingScriptService;
import org.eddy.swing.entity.ValidateExecuter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eddy on 2017/1/7.
 */
@Service
public class SwingScriptManagerImpl implements SwingScriptManager {

    @Autowired
    private SwingScriptService scriptService;

    @Override
    public void insert(ValidateExecuter executer) {
        scriptService.insert(executer);
    }

    @Override
    public List<ValidateExecuter> selectAll() {
        return scriptService.selectAll();
    }
}
