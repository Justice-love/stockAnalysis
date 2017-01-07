package org.eddy.dao.mapper.swing;

import org.eddy.swing.entity.ValidateExecuter;

import java.util.List;

/**
 * Created by eddy on 2017/1/7.
 */
public interface SwingScriptMapper {

    int insert(ValidateExecuter executer);

    List<ValidateExecuter> selectAll();
}
