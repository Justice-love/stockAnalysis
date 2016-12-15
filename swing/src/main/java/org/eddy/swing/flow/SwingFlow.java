package org.eddy.swing.flow;

import org.eddy.entity.Stock;
import org.eddy.swing.entity.Swing;
import org.eddy.swing.entity.SwingContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * swings可能并发的修改数据，所以采用ConcurrentHashMap来保证线程安全
 * Created by eddy on 16/12/15.
 */
@Component
public class SwingFlow {

    private Map<String, Swing> swings = new ConcurrentHashMap<>();

    public void flow(List<Stock> sources, Stock stock) {
        swings.values().stream().filter(s -> s.isAutoTrigger()).forEach(s -> {
            SwingContext swingContext = new SwingContext(s, stock);
            excute(swingContext);
        });

    }

    private void excute(SwingContext swingContext) {
        Swing swing = swingContext.getLastSwing();

    }
}
