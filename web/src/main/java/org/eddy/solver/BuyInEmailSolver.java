package org.eddy.solver;

import org.eddy.entity.StockWantBuy;
import org.eddy.service.NotifyService;
import org.eddy.service.StockWantBuyService;
import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.entity.exception.SwingException;
import org.eddy.swing.solver.define.SwingFlowSolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created by eddy on 2016/12/15.
 */
@Component("buyInEmailSolver")
public class BuyInEmailSolver implements SwingFlowSolver {

    private static String TO_EMAIL = "eddyxu1213@126.com";
    private static String BUY_IN = "买入提醒(%s)";

    @Autowired
    @Qualifier("email")
    private NotifyService emailService;

    @Autowired
    private StockWantBuyService stockWantBuyService;

    @Override
    public void solve(SwingValidateContext context) throws SwingException {
        StockWantBuy stockWantBu = new StockWantBuy();
        stockWantBu.setName(context.getStock().getName());
        stockWantBu.setStockCode(context.getStock().getStockCode());
        stockWantBu.setCurrentPrice(context.getStock().getPrice());
        stockWantBu.setCurrentUp(context.getStock().getUp());
        stockWantBu.setCurrentDate(context.getStock().getDate());
        stockWantBu.setCurrentTime(context.getStock().getTime());
        stockWantBu.setValidaters(context.getFlowSwings().stream().map(swing -> swing.getValidateType().name()).collect(Collectors.joining(" | ")));
        Boolean flag = stockWantBuyService.insertOrUpdateNeedNotify(stockWantBu);
        if (flag) {
            emailService.asyncNotify(context, "toBuyTemplate", TO_EMAIL, String.format(BUY_IN, stockWantBu.getName()));
        }
    }
}
