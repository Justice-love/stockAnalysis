package org.eddy.mybatis;

import org.apache.commons.lang3.StringUtils;
import org.eddy.ApplicationStart;
import org.eddy.dao.mapper.stock.StockMapper;
import org.eddy.entity.Stock;
import org.eddy.entity.Url;
import org.eddy.exception.JsoupException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by eddy on 2016/12/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationStart.class})
@ActiveProfiles(value="dev")
public class StockTest {

    @Autowired
    StockMapper stockMapper;

    @Test
    public void test() {
        Stock stock = new Stock();
        stock.setStockCode("sh0001");
        stock.setName("name");
        stock.setPrice("30.8");
        List<Stock> list = new ArrayList<>();
        list.add(stock);
        stockMapper.insert(list);
    }
}
