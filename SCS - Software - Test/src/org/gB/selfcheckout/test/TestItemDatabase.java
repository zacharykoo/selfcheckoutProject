package org.gB.selfcheckout.test;

import org.gB.selfcheckout.software.ItemDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.*;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;
import org.lsmr.selfcheckout.products.Product;

import java.math.BigDecimal;

/**
 * A test suite for org.g30.selfcheckout.ItemDatabase.
 */
public class TestItemDatabase {

    private ItemDatabase database;

    private Barcode barcode;
    private Item item;
    private Product product;

    @Before
    public void setupTests()
    {
        //Initialize database.
        database = new ItemDatabase();

        //Create entry.
        barcode = new Barcode(new Numeral[]{Numeral.one});
        item = new BarcodedItem(barcode, 1);
        product = new BarcodedProduct(barcode, "product", new BigDecimal(2), 46.5);
    }

    @Test
    public void testAddFirstValidEntry()
    {
        //Add a valid entry.
        Assert.assertEquals(database.addEntry(item, product), 1);

        //Ensure entries have been added.
        Assert.assertEquals(database.getItem(barcode).getWeight(), item.getWeight(), 0);
        Assert.assertEquals(database.getProduct(barcode).getPrice(), product.getPrice());
    }

    @Test
    public void testMultipleValidEntry()
    {
        //Add a valid entry.
        Assert.assertEquals(database.addEntry(item, product), 1);

        //Create and add another valid entry.
        Barcode barcode2 = new Barcode(new Numeral[]{Numeral.two});
        Item item2 = new BarcodedItem(barcode2, 3);
        Product product2 = new BarcodedProduct(barcode2, "product2", new BigDecimal(4), 15.4);
        Assert.assertEquals(database.addEntry(item2, product2), 1);

        //Ensure both entries have been added.
        Assert.assertEquals(database.getItem(barcode).getWeight(), item.getWeight(), 0);
        Assert.assertEquals(database.getProduct(barcode).getPrice(), product.getPrice());

        Assert.assertEquals(database.getItem(barcode2).getWeight(), item2.getWeight(), 0);
        Assert.assertEquals(database.getProduct(barcode2).getPrice(), product2.getPrice());
    }

    @Test
    public void testAddDuplicateBarcode()
    {
        //Add a valid entry.
        Assert.assertEquals(database.addEntry(item, product), 1);

        //Create another entry with the same barcode.
        Barcode barcodeEq = new Barcode(new Numeral[]{Numeral.one});
        Item itemEq = new BarcodedItem(barcodeEq, 3);
        Product productEq = new BarcodedProduct(barcodeEq, "product", new BigDecimal(4), 33.3);

        //Ensure entry returns error code.
        Assert.assertNotEquals(database.addEntry(itemEq, productEq), 1);

        //Ensure both equal barcodes still point to the first entry.
        Assert.assertEquals(database.getItem(barcode).getWeight(), item.getWeight(), 0);
        Assert.assertEquals(database.getProduct(barcode).getPrice(), product.getPrice());

        Assert.assertEquals(database.getItem(barcodeEq).getWeight(), item.getWeight(), 0);
        Assert.assertEquals(database.getProduct(barcodeEq).getPrice(), product.getPrice());
    }

    @Test
    public void testAddItemWithoutBarcode()
    {
        //Create a valid entry without a barcode.
        PriceLookupCode code = new PriceLookupCode("1234");
        Item itemPLU = new PLUCodedItem(code, 1);
        Product productPLU = new PLUCodedProduct(code, "product", new BigDecimal(2));

        //Ensure entry is still added.
        Assert.assertEquals(database.addEntry(itemPLU, productPLU), 1);
    }

    @Test
    public void testSearchInvalidBarcode()
    {
        //Add a valid entry.
        Assert.assertEquals(database.addEntry(item, product), 1);

        //Create a new barcode.
        Barcode barcode2 = new Barcode(new Numeral[]{Numeral.two});

        //Ensure searching new barcode returns null.
        Assert.assertNull(database.getItem(barcode2));
        Assert.assertNull(database.getProduct(barcode2));
    }
}
