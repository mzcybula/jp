package pojo;

public final class CommonStock extends Stock {

    public CommonStock(Symbol symbol, double lastDividend, double parValue) {
        super(symbol, lastDividend, parValue);
    }

    @Override
    public double calculateDividend(double price) {
        return getLastDividend() / price;
    }
}
