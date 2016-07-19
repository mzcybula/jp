package util;

import pojo.Stock;
import pojo.Trade;

import java.util.List;
import java.util.Set;

public final class StockCalculator {

    public static double computeVWSP(List<Trade> trades) {
        double totalValue = multiplyTradedPriceAndQuantity(trades);
        int totalQuantity = totalOfQuantity(trades);
        return totalValue / totalQuantity;
    }

    private static double multiplyTradedPriceAndQuantity(List<Trade> trades) {
        double value = 0;
        for (Trade trade : trades) {
            value += trade.getTradePrice() * trade.getQuantityOfShares();
        }
        return value;
    }

    private static int totalOfQuantity(List<Trade> trades) {
        int value = 0;
        for (Trade trade : trades) {
            value += trade.getQuantityOfShares();
        }
        return value;
    }

    public static double computeGBCEAllShareIndex(Set<Stock> stocks) {
        double value = 0;
        for (Stock stock : stocks) {
            value += stock.getLastPrice();
        }
        return Math.pow(value, 1.0 / stocks.size());
    }

    private StockCalculator() {
    }
}
