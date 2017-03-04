package org.eddy.solver;

import com.alibaba.fastjson.JSONObject;
import org.eddy.config.StockSolverConfig;
import org.eddy.entity.StockWantBuy;
import org.eddy.entity.pojo.HttpMessage;
import org.eddy.service.StockWantBuyService;
import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.entity.exception.SwingException;
import org.eddy.swing.solver.define.SwingFlowSolver;
import org.eddy.util.MarkdownUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created by Justice-love on 2017/3/4.
 */
@Component("buyInImSolver")
public class BuyInImSolver implements SwingFlowSolver {

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
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", "购买提醒");
            jsonObject.put("text", MarkdownUtil.createMarkdownContent(context));
            message.setContent(jsonObject.toJSONString());
        }
    }
}
