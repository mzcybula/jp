import dao.StockDAO;
import dao.StockDAOImpl;
import org.junit.Before;
import org.junit.Test;
import pojo.CommonStock;
import pojo.PrefferedStock;
import pojo.Stock;
import pojo.Trade;
import service.StockService;
import service.StockServiceImpl;

import static org.junit.Assert.assertEquals;

public final class StockServiceTest {

    private StockDAO stockDAO;
    private StockService service;

    @Before
    public void setup() {
        stockDAO = new StockDAOImpl();
        service = new StockServiceImpl(stockDAO);
        persistStockData();
        recordNewTrades();
    }

    @Test
    public void testCalculateCommonDividendYeld() {
        assertEquals(1, service.calculateDividendYeldBy(8, Stock.Symbol.POP), 0);
    }

    @Test
    public void testCalculatePrefferedDividendYeld() {
        assertEquals(2, service.calculateDividendYeldBy(1, Stock.Symbol.GIN), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateDividendYeld_WithZeroPrice() {
        service.calculateDividendYeldBy(0, Stock.Symbol.GIN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateDividendYeld_WithSymbolAsNull() {
        service.calculateDividendYeldBy(1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateDividendYeld_WhenStockNotExists() {
        service.calculateDividendYeldBy(1, Stock.Symbol.TEA);
    }

    @Test
    public void testCalculatePERatio() {
        assertEquals(8, service.calculatePERatio(8, Stock.Symbol.POP), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePERatio_WithZeroPrice() {
        service.calculatePERatio(0, Stock.Symbol.POP);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePERatio_WithSymbolAsNull() {
        service.calculatePERatio(8, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePERatioBy_WhenStockNotExists() {
        service.calculatePERatio(8, Stock.Symbol.TEA);
    }

    @Test
    public void testRecordTrade() {
        service.recordTrade(new Trade(10, 20, stockDAO.getStockBy(Stock.Symbol.ALE), Trade.Indicator.BUY));
        assertEquals(3, stockDAO.getAllTrades().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRecordTable_WhenTradedPriceIsZero() {
        service.recordTrade(new Trade(0, 20, stockDAO.getStockBy(Stock.Symbol.ALE), Trade.Indicator.BUY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRecordTable_WhenQuantityOfSharesIsZero() {
        service.recordTrade(new Trade(10, 0, stockDAO.getStockBy(Stock.Symbol.ALE), Trade.Indicator.BUY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRecordTable_WithSymbolAsNull() {
        service.recordTrade(new Trade(10, 20, null, Trade.Indicator.BUY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRecordTable_WithIndicatorAsNull() {
        service.recordTrade(new Trade(10, 20, stockDAO.getStockBy(Stock.Symbol.ALE), null));
    }

    @Test
    public void testCalculateVolumeWeightedStockPrice_InLastMinutes() {
        assertEquals(50, service.calculateVWSPLast(15), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateVolumeWeightedStockPrice_WithZeroMinutes() {
        assertEquals(10, service.calculateVWSPLast(0), 0);
    }

    @Test
    public void testCalculateGBCEAllShareIndex() {
        assertEquals(10, service.calculateGBCEAllShareIndex(), 0);
    }

    private void persistStockData() {
        stockDAO.saveStock(new CommonStock(Stock.Symbol.POP, 8, 100));
        stockDAO.saveStock(new PrefferedStock(Stock.Symbol.GIN, 8, 100, 0.02));
        stockDAO.saveStock(new CommonStock(Stock.Symbol.ALE, 23, 60));
        stockDAO.saveStock(new CommonStock(Stock.Symbol.JOE, 13, 250));
    }

    private void recordNewTrades() {
        service.recordTrade(new Trade(50, 100, stockDAO.getStockBy(Stock.Symbol.ALE), Trade.Indicator.BUY));
        service.recordTrade(new Trade(50, 100, stockDAO.getStockBy(Stock.Symbol.JOE), Trade.Indicator.SELL));
    }
}
