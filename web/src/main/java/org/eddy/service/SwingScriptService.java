package org.eddy.service;

import org.eddy.swing.entity.ValidateExecuter;

import java.util.List;

/**
 * Created by eddy on 2017/1/7.
 */
public interface SwingScriptService {

    void insert(ValidateExecuter executer);

    List<ValidateExecuter> selectAll();
}
