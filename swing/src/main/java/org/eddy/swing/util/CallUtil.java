package org.eddy.swing.util;

import org.apache.commons.lang3.StringUtils;
import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.entity.exception.SwingException;
import org.eddy.swing.solver.define.SwingFlowSolver;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by eddy on 2016/12/15.
 */
@Component
public class CallUtil implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    public static void call(String executor, SwingValidateContext context) throws SwingException {
        if (StringUtils.isBlank(executor)) {
            throw new SwingException("executor is blank");
        }
        SwingFlowSolver solver = (SwingFlowSolver) applicationContext.getBean(executor);
        if (null == solver) {
            throw new SwingException("can not find spring bean with name:" + executor + ", and type:SwingFlowSolver");
        }
        solver.solve(context);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CallUtil.applicationContext = applicationContext;
    }
}
