package dao;

import pojo.Stock;
import pojo.Trade;

import java.util.*;

public final class StockDAOImpl implements StockDAO {

    private Set<Stock> stockSet = new HashSet<>();
    private List<Trade> tradeList = new ArrayList<>();

    @Override
    public void saveStock(Stock stock) {
        stockSet.add(stock);
    }

    @Override
    public void saveTrade(Trade trade) {
        tradeList.add(trade);
    }

    @Override
    public Stock getStockBy(Stock.Symbol symbol) {
        for (Stock stock : stockSet) {
            if (stock.getSymbol().equals(symbol)) {
                return stock;
            }
        }
        return null;
    }

    @Override
    public List<Trade> getTradesNotOlderThan(Date date) {
        List<Trade> list = new ArrayList<>();
        for (Trade trade : tradeList) {
            if (trade.getTimestamp().after(date)) {
                list.add(trade);
            }
        }
        return list;
    }

    @Override
    public List<Trade> getAllTrades() {
        return tradeList;
    }

    @Override
    public Set<Stock> getAllStocksWithPriceGreaterThanZero() {
        Set<Stock> stocks = new HashSet<>();
        for (Stock stock : stockSet) {
            if (stock.getLastPrice() > 0) {
                stocks.add(stock);
            }
        }
        return stocks;
    }
}
