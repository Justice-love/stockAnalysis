package org.eddy.swing.entity;

import org.eddy.entity.Stock;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by eddy on 16/12/15.
 */
public class SwingContext {

    private List<Swing> flowSwings = new ArrayList<>();

    private Stock stock;

    public SwingContext(Swing first, Stock stock) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(stock);
        this.flowSwings.add(first);
        this.stock = stock;
    }

    public void addSwing(Swing swing) {
        Objects.requireNonNull(swing);
        this.getFlowSwings().add(swing);
    }

    public Swing getLastSwing() {
        Assert.notEmpty(flowSwings, "rule modules can't be null/empty");
        return flowSwings.get(flowSwings.size() - 1);
    }

    public List<Swing> getFlowSwings() {
        return flowSwings;
    }

    public void setFlowSwings(List<Swing> flowSwings) {
        this.flowSwings = flowSwings;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
