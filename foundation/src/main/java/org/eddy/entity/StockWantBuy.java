package org.eddy.entity;

import java.util.Date;

/**
 * Created by eddy on 16/12/16.
 */
public class StockWantBuy {

    private int id;

    private String name;

    private String stockCode;

    private String currentPrice;

    private String currentUp;

    private String currentTime;

    private String currentDate;

    private String validaters;

    private Date createTime;

    private Date updateTime;

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

    public String getValidaters() {
        return validaters;
    }

    public void setValidaters(String validaters) {
        this.validaters = validaters;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getCurrentUp() {
        return currentUp;
    }

    public void setCurrentUp(String currentUp) {
        this.currentUp = currentUp;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
}
