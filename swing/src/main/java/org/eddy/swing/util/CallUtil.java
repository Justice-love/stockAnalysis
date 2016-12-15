package org.eddy.swing.util;

import org.apache.commons.lang3.StringUtils;
import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.entity.exception.SwingException;
import org.eddy.swing.solver.define.SwingFlowSolver;

/**
 * Created by eddy on 2016/12/15.
 */
public class CallUtil {

    public static void call(String glass, SwingValidateContext context) throws SwingException {
        if (StringUtils.isBlank(glass)) {
            throw new SwingException("executor is blank");
        }
        try {
            Class executor = Class.forName(glass);
            if (!SwingFlowSolver.class.isAssignableFrom(executor)) {
                throw new SwingException("executor must SwingFlowSolver");
            }
            SwingFlowSolver solver = (SwingFlowSolver) executor.newInstance();
            solver.solve(context);
        } catch (ClassNotFoundException e) {
            throw new SwingException("executor not a glass", e);
        } catch (IllegalAccessException e) {
            throw new SwingException(e);
        } catch (InstantiationException e) {
            throw new SwingException(e);
        }
    }
}
