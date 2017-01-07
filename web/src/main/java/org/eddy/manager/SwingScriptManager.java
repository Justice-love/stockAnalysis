package org.eddy.manager;

import org.eddy.swing.entity.Swing;
import org.eddy.swing.entity.ValidateExecuter;
import org.eddy.swing.entity.Validater;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by eddy on 2017/1/7.
 */
public interface SwingScriptManager {

    void insert(ValidateExecuter executer);

    List<ValidateExecuter> selectAll();

    Set<Map.Entry<String, Validater>> getAllSwings();
}
