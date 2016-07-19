package dao;

import pojo.Stock;
import pojo.Trade;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface StockDAO {

    void saveStock(Stock stock);

    void saveTrade(Trade trade);

    Stock getStockBy(Stock.Symbol symbol);

    List<Trade> getTradesNotOlderThan(Date date);

    List<Trade> getAllTrades();

    Set<Stock> getAllStocksWithPriceGreaterThanZero();
}
