package org.eddy.swing.flow;

import org.apache.commons.lang3.StringUtils;
import org.eddy.entity.Stock;
import org.eddy.swing.SwingContext;
import org.eddy.swing.entity.Swing;
import org.eddy.swing.entity.SwingValidateContext;
import org.eddy.swing.entity.Validater;
import org.eddy.swing.entity.exception.SwingException;
import org.eddy.swing.util.CallUtil;
import org.eddy.util.BeansUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eddy on 16/12/15.
 */
@Component
public class SwingFlow {

    private final Pattern oneField = Pattern.compile("^\\{\\w+\\}$");
    private final Pattern computer = Pattern.compile("^\\{\\w+\\}\\s+[+|-|*|/]\\s+\\{\\w+\\}$");
    private final DecimalFormat df = new DecimalFormat("0.00");

    @PostConstruct
    public void init() {
        df.setRoundingMode(RoundingMode.HALF_UP);
    }

    public void flow(SortedMap<String, List<Stock>> groupStocks, Stock stock, Swing swing) throws SwingException{
        SwingValidateContext swingValidateContext = new SwingValidateContext(swing, stock, groupStocks);
        excute(swingValidateContext);
    }

    private void excute(SwingValidateContext swingValidateContext) throws SwingException{
        Swing swing = swingValidateContext.getLastSwing();
        Validater validater = swing.getValidateType();
        boolean result = validater.validate(swingValidateContext.getGroupStocks(), swing.getExpression(), genExpect(swing.getExpect(), swingValidateContext.getStock()));
        if (result && swing.hasChild()) {
            swingValidateContext.addSwingChain(swing.getChild());
            excute(swingValidateContext);
        } else if (result){
            CallUtil.call(swing.getExecutor(), swingValidateContext);
        } else if (!result && StringUtils.isNotBlank(swing.getOrElse())) {
            Swing or = SwingContext.getContext().getSwings().get(swing.getOrElse()).stream().filter(s -> StringUtils.equals(s.getId(), swing.getOrElse())).findFirst().orElseThrow(() -> new SwingException("not fount swing id: " + swing.getOrElse()));
            swingValidateContext.addSwingChain(or);
            excute(swingValidateContext);
        }
    }

    private String genExpect(String expect, Stock stock) throws SwingException{
        if (StringUtils.isBlank(expect) || null == stock) {
            return StringUtils.EMPTY;
        }
        if (!expect.contains("{") && !expect.contains("}")) {
            return expect;
        }
        String content = expect.trim();
        Matcher oneFieldM = oneField.matcher(content);
        Matcher computerM = computer.matcher(content);
        if (oneFieldM.matches()) {
            String key = content.substring(0, content.length() - 1).substring(1);
            try {
                return BeansUtil.readPropertie(stock, key);
            } catch (Exception e) {
                throw new SwingException("genExpect error, content:" + content, e);
            }
        } else if (computerM.matches()) {
            String[] arr = content.split("\\s");
            String key1 = arr[0].substring(0, content.length() - 1).substring(1);
            String key2 = arr[2].substring(0, content.length() - 1).substring(1);
            try {
                double value1 = Double.parseDouble(BeansUtil.readPropertie(stock, key1));
                double value2 = Double.parseDouble(BeansUtil.readPropertie(stock, key2));
                switch (arr[1]) {
                    case "+":
                        return Double.toString(value1 + value2);
                    case "-":
                        double result = value1 - value2;
                        return df.format(result);
                    case "*":
                        result = value1 * value2;
                        return df.format(result);
                    case "/":
                        return value2 == 0 ? df.format(value1) : df.format(value1 / value2);
                    default:
                        throw new SwingException("not support, content:" + content);
                }
            } catch (Exception e) {
                throw new SwingException("computer genExpect error, content:" + content, e);
            }
        }
        return expect;
    }
}
