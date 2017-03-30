package org.eddy.solver;

import org.eddy.config.StockSolverConfig;
import org.eddy.solver.imSolver.SaleOutImSolver;
import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.entity.exception.SwingException;
import org.eddy.swing.entity.httpMessage.HttpMessage;
import org.eddy.swing.solver.define.SwingFlowSolver;
import org.eddy.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Justice-love on 2017/3/4.
 */
@Component("saleOutImSolver")
public class MainSaleOutImSolver extends SaleOutImSolver {

    @Override
    protected String chooseToken(StockSolverConfig config) {
        return config.getSaleToken();
    }
}
