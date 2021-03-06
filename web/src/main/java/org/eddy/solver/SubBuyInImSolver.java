package org.eddy.solver;

import org.eddy.config.StockSolverConfig;
import org.eddy.solver.imSolver.BuyInImSolver;
import org.springframework.stereotype.Component;

/**
 * Created by eddy on 2017/3/29.
 */
@Component("subBuyInImSolver")
public class SubBuyInImSolver extends BuyInImSolver {

    @Override
    protected String chooseToken(StockSolverConfig config) {
        return config.getSubToken();
    }
}
