package org.eddy.swing.entity.groovy;

import org.eddy.entity.Stock;
import org.eddy.swing.entity.Validater;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.SortedMap;

/**
 * Created by eddy on 2016/12/28.
 */
@Component
public class DefaultValidater extends Validater{

    @PostConstruct
    public void init() {
        register(name());
    }

    @Override
    public boolean validate(SortedMap<String, List<Stock>> groupStocks, String expression, String expect) {
        return true;
    }

    @Override
    public String getDescription() {
        return "默认校验器";
    }

    @Override
    public String name() {
        return "defaultValidater";
    }
}
