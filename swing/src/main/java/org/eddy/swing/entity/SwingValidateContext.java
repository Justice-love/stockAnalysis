package org.eddy.swing.entity;

import org.eddy.entity.Stock;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.SortedMap;

/**
 * Created by eddy on 16/12/15.
 */
public class SwingValidateContext {

    private List<ValidateSwing> flowSwings = new ArrayList<>();

    private Stock stock;

    private SortedMap<String, List<Stock>> groupStocks;

    public SwingValidateContext(ValidateSwing first, Stock stock, SortedMap<String, List<Stock>> groupStocks) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(stock);
        this.flowSwings.add(first);
        this.stock = stock;
        this.groupStocks = groupStocks;
    }

    public void addSwingChain(ValidateSwing swing) {
        Objects.requireNonNull(swing);
        this.getFlowSwings().add(swing);
    }

    public ValidateSwing getLastSwing() {
        Assert.notEmpty(flowSwings, "rule modules can't be null/empty");
        return flowSwings.get(flowSwings.size() - 1);
    }

    public List<ValidateSwing> getFlowSwings() {
        return flowSwings;
    }

    public void setFlowSwings(List<ValidateSwing> flowSwings) {
        this.flowSwings = flowSwings;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public SortedMap<String, List<Stock>> getGroupStocks() {
        return groupStocks;
    }

    public void setGroupStocks(SortedMap<String, List<Stock>> groupStocks) {
        this.groupStocks = groupStocks;
    }
}
