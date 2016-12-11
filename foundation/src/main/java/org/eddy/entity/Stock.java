package org.eddy.entity;

/**
 * Created by eddy on 2016/12/7.
 */
public class Stock {

    private int id;

    private String name;

    private String price;

    private String up;

    private String upPrice;

    private String buy;

    private String sale;

    private String todayBegin;

    private String yesterdayEnd;

    private String date;

    private String  time;

    private String stockCode;

    private String errorContent;

    public Stock() {
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getUpPrice() {
        return upPrice;
    }

    public void setUpPrice(String upPrice) {
        this.upPrice = upPrice;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTodayBegin() {
        return todayBegin;
    }

    public void setTodayBegin(String todayBegin) {
        this.todayBegin = todayBegin;
    }

    public String getYesterdayEnd() {
        return yesterdayEnd;
    }

    public void setYesterdayEnd(String yesterdayEnd) {
        this.yesterdayEnd = yesterdayEnd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getErrorContent() {
        return errorContent;
    }

    public void setErrorContent(String errorContent) {
        this.errorContent = errorContent;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
