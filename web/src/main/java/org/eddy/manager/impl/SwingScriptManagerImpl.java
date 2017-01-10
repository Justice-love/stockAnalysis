package org.eddy.manager.impl;

import org.eddy.manager.SwingScriptManager;
import org.eddy.service.SwingScriptService;
import org.eddy.service.SwingService;
import org.eddy.swing.entity.Swing;
import org.eddy.swing.entity.ValidateExecuter;
import org.eddy.swing.entity.Validater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by eddy on 2017/1/7.
 */
@Service
public class SwingScriptManagerImpl implements SwingScriptManager {

    @Autowired
    private SwingScriptService scriptService;

    @Autowired
    private SwingService swingService;

    @Override
    public void insert(ValidateExecuter executer) {
        scriptService.insert(executer);
    }

    @Override
    public List<ValidateExecuter> selectAll() {
        return scriptService.selectAll();
    }

    public Set<Map.Entry<String, Validater>> getAllSwings() {
        return Validater.getAll();
    }

    @Override
    public void insertSwings(List<Swing> list) {
        swingService.insert(list);
    }
}
