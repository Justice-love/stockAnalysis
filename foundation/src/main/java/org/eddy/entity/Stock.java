package org.eddy.entity;

/**
 * Created by eddy on 2016/12/7.
 */
public class Stock {

    public static final Boolean HAS_ERROR = true;

    public static final Boolean NO_ERROR = false;

    private int id;

    private String name;

    private String price;

    private String up;

    private String upPrice;

    private String todayBegin;

    private String yesterdayEnd;

    private String date;

    private String  time;

    private String stockCode;

    private String errorContent;

    private boolean hasError;

    private String buy1;

    private String buy1Price;

    private String  buy2;

    private String buy2Price;

    private String buy3;

    private String buy3Price;

    private String sale1;

    private String sale1Price;

    private String sale2;

    private String sale2Price;

    private String sale3;

    private String sale3Price;

    private String toBuyPrice;

    private String toSalePrice;

    private String dealStock;

    private String dealMoney;

    public Stock() {
    }

    public Stock(String name, String stockCode) {
        this.name = name;
        this.stockCode = stockCode;
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

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getBuy1() {
        return buy1;
    }

    public void setBuy1(String buy1) {
        this.buy1 = buy1;
    }

    public String getBuy1Price() {
        return buy1Price;
    }

    public void setBuy1Price(String buy1Price) {
        this.buy1Price = buy1Price;
    }

    public String getBuy2() {
        return buy2;
    }

    public void setBuy2(String buy2) {
        this.buy2 = buy2;
    }

    public String getBuy2Price() {
        return buy2Price;
    }

    public void setBuy2Price(String buy2Price) {
        this.buy2Price = buy2Price;
    }

    public String getBuy3() {
        return buy3;
    }

    public void setBuy3(String buy3) {
        this.buy3 = buy3;
    }

    public String getBuy3Price() {
        return buy3Price;
    }

    public void setBuy3Price(String buy3Price) {
        this.buy3Price = buy3Price;
    }

    public String getSale1() {
        return sale1;
    }

    public void setSale1(String sale1) {
        this.sale1 = sale1;
    }

    public String getSale1Price() {
        return sale1Price;
    }

    public void setSale1Price(String sale1Price) {
        this.sale1Price = sale1Price;
    }

    public String getSale2() {
        return sale2;
    }

    public void setSale2(String sale2) {
        this.sale2 = sale2;
    }

    public String getSale2Price() {
        return sale2Price;
    }

    public void setSale2Price(String sale2Price) {
        this.sale2Price = sale2Price;
    }

    public String getSale3() {
        return sale3;
    }

    public void setSale3(String sale3) {
        this.sale3 = sale3;
    }

    public String getSale3Price() {
        return sale3Price;
    }

    public void setSale3Price(String sale3Price) {
        this.sale3Price = sale3Price;
    }

    public String getToBuyPrice() {
        return toBuyPrice;
    }

    public void setToBuyPrice(String toBuyPrice) {
        this.toBuyPrice = toBuyPrice;
    }

    public String getToSalePrice() {
        return toSalePrice;
    }

    public void setToSalePrice(String toSalePrice) {
        this.toSalePrice = toSalePrice;
    }

    public String getDealStock() {
        return dealStock;
    }

    public void setDealStock(String dealStock) {
        this.dealStock = dealStock;
    }

    public String getDealMoney() {
        return dealMoney;
    }

    public void setDealMoney(String dealMoney) {
        this.dealMoney = dealMoney;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", up='" + up + '\'' +
                ", upPrice='" + upPrice + '\'' +
                ", todayBegin='" + todayBegin + '\'' +
                ", yesterdayEnd='" + yesterdayEnd + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", stockCode='" + stockCode + '\'' +
                '}';
    }
}
