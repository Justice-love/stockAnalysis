package org.eddy.swing.entity.groovy.handler;

import org.eddy.entity.Stock;
import org.eddy.swing.entity.Validater;
import org.eddy.swing.entity.groovy.execute.ValidateExecuter;

import java.util.List;
import java.util.SortedMap;

/**
 * Created by eddy on 2017/1/7.
 */
public class ValidaterHandler extends Validater{

    private ValidateExecuter validateExecuter;

    public ValidaterHandler() {
        register(name());
    }

    @Override
    public boolean validate(SortedMap<String, List<Stock>> groupStocks, String expression, String expect) {
        return validateExecuter.execute(groupStocks, expression, expect);
    }

    @Override
    public String getDescription() {
        return validateExecuter.getDescription();
    }

    @Override
    public String name() {
        return validateExecuter.getScriptName();
    }
}
