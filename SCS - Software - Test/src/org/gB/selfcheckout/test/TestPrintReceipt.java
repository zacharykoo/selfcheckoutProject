package org.gB.selfcheckout.test;

import java.math.BigDecimal;

import org.gB.selfcheckout.software.ItemDatabase;
import org.gB.selfcheckout.software.Main;
import org.gB.selfcheckout.software.PrintReceipt;
import org.gB.selfcheckout.software.State;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.Product;

public class TestPrintReceipt {
    private State state; // Stores a program state for testing.
    private Item item;
    
    @Before
    public void setup() {
        //Initialize State.
        try {
        	state = Main.init(100, 1);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        assert state != null;

        ItemDatabase database = state.idb;

        //Add an entry to database.
        Barcode barcode = new Barcode(new Numeral[]{Numeral.one});
        item = new BarcodedItem(barcode, 1);
        Product product = new BarcodedProduct(barcode, "product", new BigDecimal(2), 50.0);
        database.addEntry(item, product);
    }

    @Test
    public void testPrintReceipt() {
        PrintReceipt printReceipt = new PrintReceipt(state);
        printReceipt.printReceipt();
    }
}
