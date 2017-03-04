package org.eddy.solver;

import com.alibaba.fastjson.JSONObject;
import org.eddy.config.StockSolverConfig;
import org.eddy.entity.StockWantBuy;
import org.eddy.service.StockWantBuyService;
import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.entity.exception.SwingException;
import org.eddy.swing.entity.httpMessage.HttpMessage;
import org.eddy.swing.solver.define.SwingFlowSolver;
import org.eddy.swing.util.MarkdownUtil;
import org.eddy.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Created by Justice-love on 2017/3/4.
 */
@Component("buyInImSolver")
public class BuyInImSolver implements SwingFlowSolver {

    private static Logger logger = LoggerFactory.getLogger(BuyInImSolver.class);

    @Autowired
    private StockWantBuyService stockWantBuyService;

    @Autowired
    private StockSolverConfig config;

    @Override
    public void solve(SwingValidateContext context) throws SwingException {
        StockWantBuy stockWantBu = new StockWantBuy();
        stockWantBu.setName(context.getStock().getName());
        stockWantBu.setStockCode(context.getStock().getStockCode());
        stockWantBu.setCurrentPrice(context.getStock().getPrice());
        stockWantBu.setCurrentUp(context.getStock().getUp());
        stockWantBu.setCurrentDate(context.getStock().getDate());
        stockWantBu.setCurrentTime(context.getStock().getTime());
        stockWantBu.setValidaters(context.getFlowSwings().stream().map(swing -> swing.getSwing().getValidateType().name()).collect(Collectors.joining(" | ")));
        Boolean flag = stockWantBuyService.insertOrUpdateNeedNotify(stockWantBu);
        if (flag) {
            HttpMessage message = new HttpMessage();
            message.setProtocol(HttpMessage.ProtocolEnum.HTTPS);
            message.setUrl(new StringBuilder(config.getUrl()).append("?").append(config.getArg()).append("=").append(config.getToken()).toString());
            message.setMessageTypeEnum(HttpMessage.MessageTypeEnum.markdown);
            message.setContent(HttpMessage.MessageTypeEnum.markdown.createBuyContent(context));
            try {
                boolean result = HttpUtil.sendMsg(message);
                if (!result) {
                    logger.error("send dingding notify error: buy in");
                }
            } catch (IOException e) {
                logger.error("notify sale out error", e);
            }
        }
    }
}
