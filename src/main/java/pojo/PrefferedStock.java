package pojo;

public final class PrefferedStock extends Stock {

    private final double fixedDividend;

    public PrefferedStock(Symbol symbol, double lastDividend, double parValue, double fixedDividend) {
        super(symbol, lastDividend, parValue);
        this.fixedDividend = fixedDividend;
    }

    @Override
    public double calculateDividend(double price) {
        return fixedDividend * getParValue() / price;
    }
}
