package org.eddy.util;

import org.eddy.entity.Stock;
import org.eddy.swing.entity.Swing;
import org.eddy.swing.entity.SwingValidateContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Created by Justice-love on 2017/3/4.
 */
public class MarkdownUtil {

    public static String createMarkdownContent(SwingValidateContext context) {
        Objects.requireNonNull(context.getStock(), "context.getStock() is null");
        StringBuilder builder = new StringBuilder("## 股票购买提醒\n");
        builder.append("#### 基本信息\n");
        builder.append("* 股票名称：").append(context.getStock().getName()).append("\n");
        builder.append("* 股票代码：").append(context.getStock().getStockCode()).append("\n");
        builder.append("* 当前价格：").append(context.getStock().getPrice()).append("\n");
        builder.append("* 当前涨幅：").append(context.getStock().getUp()).append("\n");
        builder.append("* 当前日期：").append(context.getStock().getDate()).append("\n");
        builder.append("* 当前时间：").append(context.getStock().getTime()).append("\n");
        builder.append("* 推送时间：").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
        builder.append("* 通过规则：\n");
        context.getFlowSwings().stream().forEach(s -> {
            //${item.swing.validateType.name()} + '&nbsp;(' + ${item.swing.validateType.description} + ')&nbsp;' + ${item.success}"
            builder.append("\t* ")
                    .append(s.getSwing().getValidateType().name())
                    .append(" (")
                    .append(s.getSwing().getValidateType().getDescription())
                    .append(") ")
                    .append(s.isSuccess())
                    .append("\n");
        });
        builder.append("#### 趋势图\n");
        builder.append("![趋势图](http://image.sinajs.cn/newchart/daily/n/").append(context.getStock().getStockCode()).append(".gif)\n");
        builder.append("#### 分时线\n");
        builder.append("![分时线](http://image.sinajs.cn/newchart/min/n/").append(context.getStock().getStockCode()).append(".gif)\n");
        return  builder.toString();
    }
}
