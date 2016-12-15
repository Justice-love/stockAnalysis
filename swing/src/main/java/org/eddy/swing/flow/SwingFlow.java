package org.eddy.swing.flow;

import org.apache.commons.lang3.StringUtils;
import org.eddy.entity.Stock;
import org.eddy.swing.SwingContext;
import org.eddy.swing.entity.Swing;
import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.entity.Validater;
import org.eddy.swing.entity.exception.SwingException;
import org.eddy.swing.util.CallUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by eddy on 16/12/15.
 */
@Component
public class SwingFlow {


    public void flow(SortedMap<String, List<Stock>> groupStocks, Stock stock, Swing swing) throws SwingException{
        SwingValidateContext swingValidateContext = new SwingValidateContext(swing, stock, groupStocks);
        excute(swingValidateContext);
    }

    private void excute(SwingValidateContext swingValidateContext) throws SwingException{
        Swing swing = swingValidateContext.getLastSwing();
        Validater validater = swing.getValidateType();
        boolean result = validater.validate(swingValidateContext.getGroupStocks(), swing.getExpression(), swing.getExpect());
        if (result && swing.hasChild()) {
            swingValidateContext.addSwingChain(swing.getChild());
            excute(swingValidateContext);
        } else if (result){
            CallUtil.call(swing.getExecutor(), swingValidateContext);
        } else if (!result && StringUtils.isNotBlank(swing.getOrElse())) {
            Swing or = SwingContext.getContext().getSwings().get(swing.getOrElse()).stream().filter(s -> StringUtils.equals(s.getId(), swing.getOrElse())).findFirst().orElseThrow(() -> new SwingException("not fount swing id: " + swing.getOrElse()));
            swingValidateContext.addSwingChain(or);
            excute(swingValidateContext);
        }
    }
}
