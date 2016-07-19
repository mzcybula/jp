package service;

import dao.StockDAO;
import pojo.Stock;
import pojo.Trade;
import util.DateUtil;
import util.Preconditions;
import util.StockCalculator;

import java.util.Date;

public final class StockServiceImpl implements StockService {

    private final StockDAO stockDAO;

    public StockServiceImpl(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }

    @Override
    public double calculateDividendYeldBy(double price, Stock.Symbol symbol) {
        Preconditions.checkNotNull(symbol);
        Stock stock = stockDAO.getStockBy(symbol);
        return Preconditions.checkNotNull(stock).calculateDividendYeldBy(price);
    }

    @Override
    public double calculatePERatio(double price, Stock.Symbol symbol) {
        Preconditions.checkNotNull(symbol);
        Stock stock = stockDAO.getStockBy(symbol);
        return Preconditions.checkNotNull(stock).calculatePERatioBy(price);
    }

    @Override
    public void recordTrade(Trade trade) {
        Preconditions.checkNotNull(trade);
        Preconditions.checkArgument(
                trade.getTradePrice() <= 0 || trade.getQuantityOfShares() <= 0 ||
                        trade.getStock() == null || trade.getIndicator() == null);
        stockDAO.saveTrade(trade);
        trade.getStock().setLastPrice(trade.getTradePrice());
    }

    @Override
    public double calculateVWSPLast(int minutes) {
        Preconditions.checkArgument(minutes <= 0);
        Date date = DateUtil.subtractFromCurrentTime(minutes);
        return StockCalculator.computeVWSP(stockDAO.getTradesNotOlderThan(date));
    }

    @Override
    public double calculateGBCEAllShareIndex() {
        return StockCalculator.computeGBCEAllShareIndex(stockDAO.getAllStocksWithPriceGreaterThanZero());
    }
}
