package pojo;

import java.util.Date;

public class Trade {

    private Date timestamp = new Date();

    private double tradePrice;
    private int quantityOfShares;

    private Stock stock;
    private Indicator indicator;

    public Trade(double tradePrice, int quantityOfShares, Stock stock, Indicator indicator) {
        this.tradePrice = tradePrice;
        this.quantityOfShares = quantityOfShares;
        this.stock = stock;
        this.indicator = indicator;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public double getTradePrice() {
        return tradePrice;
    }

    public int getQuantityOfShares() {
        return quantityOfShares;
    }

    public Stock getStock() {
        return stock;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public enum Indicator {
        BUY, SELL;
    }
}
