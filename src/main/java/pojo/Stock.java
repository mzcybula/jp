package pojo;

import util.Preconditions;

public abstract class Stock {

    private final Symbol symbol;
    private final double lastDividend;
    private final double parValue;
    private double lastPrice;

    Stock(Symbol symbol, double lastDividend, double parValue) {
        this.symbol = symbol;
        this.lastDividend = lastDividend;
        this.parValue = parValue;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    double getLastDividend() {
        return lastDividend;
    }

    double getParValue() {
        return parValue;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public double calculatePERatioBy(double price) {
        Preconditions.checkArgument(price <= 0);
        return price / calculateDividend(price);
    }

    public double calculateDividendYeldBy(double price) {
        Preconditions.checkArgument(price <= 0);
        return calculateDividend(price);
    }

    protected abstract double calculateDividend(double price);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return symbol == stock.symbol;
    }

    @Override
    public int hashCode() {
        return symbol != null ? symbol.hashCode() : 0;
    }

    public enum Symbol {
        TEA, POP, ALE, GIN, JOE
    }
}
