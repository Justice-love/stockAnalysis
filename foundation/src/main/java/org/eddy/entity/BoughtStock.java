package org.eddy.entity;

import java.util.Date;

/**
 * Created by eddy on 2016/12/17.
 */
public class BoughtStock {

    private int id;

    private String name;

    private String stockCode;

    private Date buyTime;

    private String buyPrice;

    private boolean removed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public String toString() {
        return "BoughtStock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", buyTime=" + buyTime +
                ", buyPrice='" + buyPrice + '\'' +
                '}';
    }
}
