import org.junit.Assert;
import org.junit.Test;
import pojo.CommonStock;
import pojo.Stock;

import static org.junit.Assert.*;

//Test for coverage of equals and hashcode methods
public class StockTest {

    @Test
    public void testEquals() {
        Stock stock1 = new CommonStock(Stock.Symbol.GIN, 10, 10);
        Stock stock2 = new CommonStock(Stock.Symbol.GIN, 10, 10);
        assertEquals(stock1, stock2);
    }

    @Test
    public void testEquals_WithDiffrentSymbol() {
        Stock stock1 = new CommonStock(Stock.Symbol.GIN, 10, 10);
        Stock stock2 = new CommonStock(Stock.Symbol.ALE, 10, 10);
        assertNotEquals(stock1, stock2);
    }

}
