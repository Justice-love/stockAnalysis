package org.eddy.swing.flow;

import org.eddy.entity.Stock;
import org.eddy.swing.entity.Swing;
import org.eddy.swing.entity.SwingValidateContext;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by eddy on 16/12/15.
 */
public class SwingFlow {


    public void flow(List<Stock> sources, Stock stock, Swing swing) {
        SwingValidateContext swingValidateContext = new SwingValidateContext(swing, stock, sources);

    }
}
