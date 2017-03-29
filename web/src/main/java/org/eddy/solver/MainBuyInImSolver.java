package org.eddy.solver;

import org.eddy.config.StockSolverConfig;
import org.eddy.solver.imSolver.BuyInImSolver;
import org.springframework.stereotype.Component;

/**
 * Created by eddy on 2017/3/29.
 */
@Component("buyInImSolver")
public class MainBuyInImSolver extends BuyInImSolver {

    @Override
    protected String chooseToken(StockSolverConfig config) {
        return config.getMainToken();
    }
}
