import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class MoneyTest {
	
	private Money f12CHF;
    private Money f14CHF;
    private Money f7USD;
    private Money f21USD;

    @Before
    public void setUp() {
        f12CHF = new Money(12, "CHF");
        f14CHF = new Money(14, "CHF");
        f7USD = new Money(7, "USD");
        f21USD = new Money(21, "USD");
    }

    @Test
    public void testSimpleAdd() {
        Money expected = new Money(26, "CHF");
        IMoney result = f12CHF.add(f14CHF);
        assertEquals(expected, result);
    }

    @Test
    public void testEquals() {
        assertTrue(!f12CHF.equals(null));
        assertEquals(f12CHF, f12CHF);
        assertEquals(f12CHF, new Money(12, "CHF"));
        assertTrue(!f12CHF.equals(f14CHF));
    }

    @Test
    public void testMixedSimpleAdd() {
        // [12 CHF] + [7 USD] == {[12 CHF][7 USD]}
        Money bag[] = { f12CHF, f7USD };
        MoneyBag expected = new MoneyBag(bag);
        assertEquals(expected, f12CHF.add(f7USD));
    }

    @Test
    public void testBagSimpleAdd() {
        // {[12 CHF][7 USD]} + [14 CHF] == {[26 CHF][7 USD]}
        Money bag1[] = { f12CHF, f7USD };
        MoneyBag mb1 = new MoneyBag(bag1);
        Money bag2[] = { new Money(26, "CHF"), f7USD };
        MoneyBag expected = new MoneyBag(bag2);
        assertEquals(expected, mb1.add(f14CHF));
    }

    @Test
    public void testSimpleBagAdd() {
        // [14 CHF] + {[12 CHF][7 USD]} == {[26 CHF][7 USD]}
        Money bag1[] = { f12CHF, f7USD };
        MoneyBag mb1 = new MoneyBag(bag1);
        Money bag2[] = { new Money(26, "CHF"), f7USD };
        MoneyBag expected = new MoneyBag(bag2);
        assertEquals(expected, f14CHF.add(mb1));
    }

    @Test
    public void testBagBagAdd() {
        // {[12 CHF][7 USD]} + {[14 CHF][21 USD]} == {[26 CHF][28 USD]}
        Money bag1[] = { f12CHF, f7USD };
        Money bag2[] = { f14CHF, f21USD };
        MoneyBag mb1 = new MoneyBag(bag1);
        MoneyBag mb2 = new MoneyBag(bag2);
        
        Money expectedBag[] = { new Money(26, "CHF"), new Money(28, "USD") };
        MoneyBag expected = new MoneyBag(expectedBag);
        assertEquals(expected, mb1.add(mb2));
    }
    
    @Test
    public void testSimplify() {
        // Test simplification: adding negative money to reduce bag to single currency
        Money bag[] = { new Money(26, "CHF"), f7USD };
        MoneyBag mb = new MoneyBag(bag);
        Money negativeUSD = new Money(-7, "USD");
        
        // Should simplify to just 26 CHF (Money, not MoneyBag)
        IMoney result = mb.add(negativeUSD);
        assertEquals(new Money(26, "CHF"), result);
        assertTrue(result instanceof Money); // Verify it's simplified to Money
    }

    @Test
    public void testSimplifyToEmpty() {
        // Test partial cancellation that still leaves multiple currencies
        Money bag[] = { f12CHF, f7USD };
        MoneyBag mb = new MoneyBag(bag);
        Money negativePartialCHF = new Money(-5, "CHF"); // Only partial cancellation
        
        IMoney result = mb.add(negativePartialCHF);
        
        // Should still be a MoneyBag with 2 currencies: {7 CHF, 7 USD}
        assertTrue(result instanceof MoneyBag);
        MoneyBag expected = new MoneyBag(new Money(7, "CHF"), f7USD);
        assertEquals(expected, result);
    }


}
