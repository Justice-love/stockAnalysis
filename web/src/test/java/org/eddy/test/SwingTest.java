package org.eddy.test;

import org.eddy.ApplicationStart;
import org.eddy.dao.mapper.stock.StockMapper;
import org.eddy.entity.Stock;
import org.eddy.swing.SwingContext;
import org.eddy.swing.entity.Swing;
import org.eddy.swing.flow.SwingFlow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by eddy on 2016/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationStart.class})
@ActiveProfiles(value="pro")
@Transactional
public class SwingTest {

    @Autowired
    StockMapper mapper;

    @Autowired
    SwingFlow swingFlow;

    @Test
    public void testGetContent() {
        mapper.selectGroup(mapper.selectMaxDate()).stream().forEach(s -> testSwing(s.getName(), s.getStockCode()));
    }

    public void testSwing(String name, String code) {
        List<Stock> stockList = mapper.selectSortedStocks(code);
        SortedMap<String, List<Stock>> temp =  new TreeMap<>(stockList.stream().collect(Collectors.groupingBy(s -> s.getDate())));
        Swing swing = SwingContext.getContext().getSwings().get("buy").get(0);
        try {
            swingFlow.flow(temp, stockList.get(0), swing);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
