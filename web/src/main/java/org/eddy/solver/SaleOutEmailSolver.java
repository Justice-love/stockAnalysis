package org.eddy.solver;

import org.eddy.service.NotifyService;
import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.entity.exception.SwingException;
import org.eddy.swing.solver.define.SwingFlowSolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by eddy on 16/12/16.
 */
@Component("saleOutEmailSolver")
public class SaleOutEmailSolver implements SwingFlowSolver {

    private static String TO_EMAIL = "eddyxu1213@126.com";
    private static String SALE_OUT = "卖出提醒(%s)";

    @Autowired
    @Qualifier("email")
    private NotifyService emailService;

    @Override
    public void solve(SwingValidateContext context) throws SwingException {
        emailService.notify(context, "toSaleTemplate", TO_EMAIL, String.format(SALE_OUT, context.getStock().getName()));
    }
}
