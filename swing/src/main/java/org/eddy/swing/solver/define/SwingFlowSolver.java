package org.eddy.swing.solver.define;

import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.entity.exception.SwingException;

/**
 * Created by eddy on 2016/12/15.
 */
public interface SwingFlowSolver {

    void solve(SwingValidateContext context) throws SwingException;
}
