package service;

import pojo.Stock;
import pojo.Trade;

public interface StockService {

    double calculateDividendYeldBy(double price, Stock.Symbol symbol);

    double calculatePERatio(double price, Stock.Symbol symbol);

    void recordTrade(Trade trade);

    double calculateVWSPLast(int minutes);

    double calculateGBCEAllShareIndex();
}