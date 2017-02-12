package org.eddy.manager.impl;

import org.eddy.manager.SwingScriptManager;
import org.eddy.service.SwingScriptService;
import org.eddy.service.SwingService;
import org.eddy.swing.entity.Swing;
import org.eddy.swing.entity.ValidateExecuter;
import org.eddy.swing.entity.Validater;
import org.eddy.swing.util.GroovyUtil;
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
        List<ValidateExecuter> list =  scriptService.selectAll();
        for (ValidateExecuter executer : list) {
            try {
                GroovyUtil.genGroovyValidater(executer.getScript());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Set<Map.Entry<String, Validater>> getAllSwings() {
        return Validater.getAll();
    }

    @Override
    public void insertSwings(List<Swing> list) {
        swingService.insert(list);
    }
}
