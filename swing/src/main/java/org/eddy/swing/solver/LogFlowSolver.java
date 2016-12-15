package org.eddy.swing.solver;

import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.entity.exception.SwingException;
import org.eddy.swing.solver.define.SwingFlowSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by eddy on 2016/12/15.
 */
public class LogFlowSolver implements SwingFlowSolver {
    private static final Logger logger = LoggerFactory.getLogger(LogFlowSolver.class);
    @Override
    public void solve(SwingValidateContext context) throws SwingException {
        logger.info("execute LogFlowSolver");
        logger.info("stock name:" + context.getStock().getName() + ", code:" + context.getStock().getStockCode());
        context.getFlowSwings().stream().forEach(s -> {
            logger.info("execute swing" + s.getId() + ", " + s.getValidateType().name());
        });
    }
}
