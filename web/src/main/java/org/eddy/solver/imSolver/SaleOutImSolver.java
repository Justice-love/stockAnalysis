package org.eddy.solver.imSolver;

import org.eddy.config.StockSolverConfig;
import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.entity.exception.SwingException;
import org.eddy.swing.entity.httpMessage.HttpMessage;
import org.eddy.swing.solver.define.SwingFlowSolver;
import org.eddy.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by eddy on 2017/3/30.
 */
public abstract class SaleOutImSolver implements SwingFlowSolver {

    private static Logger logger = LoggerFactory.getLogger(SaleOutImSolver.class);

    @Autowired
    private StockSolverConfig config;

    @Override
    public void solve(SwingValidateContext context) throws SwingException {
        HttpMessage message = new HttpMessage();
        message.setProtocol(HttpMessage.ProtocolEnum.HTTPS);
        message.setUrl(new StringBuilder(config.getUrl()).append("?").append(config.getArg()).append("=").append(chooseToken(config)).toString());
        message.setMessageTypeEnum(HttpMessage.MessageTypeEnum.markdown);
        message.setContent(HttpMessage.MessageTypeEnum.markdown.createSaleContent(context));
        try {
            boolean result = HttpUtil.sendMsg(message);
            if (!result) {
                logger.error("send dingding notify error: sale out");
            }
        } catch (IOException e) {
            logger.error("notify sale out error", e);
        }
    }

    protected abstract String chooseToken(StockSolverConfig config);
}
