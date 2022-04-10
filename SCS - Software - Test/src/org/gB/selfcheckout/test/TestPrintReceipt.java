package org.gB.selfcheckout.test;

import java.math.BigDecimal;

import org.gB.selfcheckout.software.ItemDatabase;
import org.gB.selfcheckout.software.Main;
import org.gB.selfcheckout.software.PrintReceipt;
import org.gB.selfcheckout.software.State;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.EmptyException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

public class TestPrintReceipt {
    private State state; // Stores a program state for testing.
    private Item item;
    
    private ItemDatabase database;
	private Numeral[] numeral1;
	private Numeral[] numeral2;
	private Barcode barcode1;
	private Barcode barcode2;
	private BarcodedProduct appleProduct;
	private BarcodedProduct watermelonProduct;
	private PriceLookupCode pluCode;
	private PLUCodedProduct pluProduct;
	private PrintReceipt printReceipt;
    
    @Before
    public void setup() {
        //Initialize State.
        try {
        	state = Main.init(10000, 10);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        assert state != null;

        database = state.idb;
        printReceipt = this.state.printReceipt;

        // PLU Coded product
        pluCode = new PriceLookupCode("12345");
        pluProduct = new PLUCodedProduct(pluCode, "Orange", BigDecimal.valueOf(3));
        
        // "Apple" product, barcode = 10, price = 2, weight = 100.0
 		numeral1 = new Numeral[2];
 		numeral1[0] = Numeral.one; numeral1[1] = Numeral.zero; // 10
 		barcode1 = new Barcode(numeral1); // barcode is 10
 		appleProduct = new BarcodedProduct(barcode1, "Apple", BigDecimal.valueOf(2), 100.0);
 				
 		// "Watermelon" product, barcode = 11, price = 10, weight = 7000.0
 		numeral2 = new Numeral[2];
 		numeral2[0] = Numeral.one; numeral2[1] = Numeral.one;  // 11
 		barcode2 = new Barcode(numeral2);
 		watermelonProduct = new BarcodedProduct(barcode2, "Watermelon", BigDecimal.valueOf(10), 7000.0);
    }

    @Test
    public void testPrintReceipt() {
    	try {
			state.scs.printer.addInk(ReceiptPrinter.MAXIMUM_INK);
		} catch (OverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			state.scs.printer.addPaper(ReceiptPrinter.MAXIMUM_PAPER);
		} catch (OverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	state.addProduct(appleProduct);
    	state.addProduct(appleProduct);
    	state.addProduct(pluProduct);
    	state.addProduct(pluProduct);
    	state.addProduct(watermelonProduct);
    	state.addProduct(pluProduct);
    	
    	state.enablePayment();
    	state.paymentTotal = BigDecimal.valueOf(50);
    	
    	printReceipt.printReceipt();
    	
    	String receipt = state.scs.printer.removeReceipt();
    	System.out.println(receipt);
    }
    
    @Test
    public void testOutAndLowOnPaper() {
    	try {
			state.scs.printer.addInk(ReceiptPrinter.MAXIMUM_INK);
		} catch (OverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			state.scs.printer.addPaper(ReceiptPrinter.MAXIMUM_PAPER);
		} catch (OverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Assert.assertFalse(state.outOfInk);
    	Assert.assertFalse(state.outOfPaper);
    	
    	state.printReceipt.setPaperRemaining(7);

    	state.addProduct(appleProduct);
    	state.addProduct(appleProduct);
    	state.addProduct(watermelonProduct);
    	state.addProduct(pluProduct);
    	state.addProduct(pluProduct);
    	state.addProduct(pluProduct);
    	
    	state.enablePayment();
    	state.paymentTotal = BigDecimal.valueOf(50);
    	
    	printReceipt.printReceipt();
    	Assert.assertTrue(state.outOfPaper);
    	Assert.assertTrue(state.lowOnPaper);
    }
    
    @Test
    public void testOutAndLowOnInk() {
    	try {
			state.scs.printer.addInk(ReceiptPrinter.MAXIMUM_INK);
		} catch (OverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			state.scs.printer.addPaper(ReceiptPrinter.MAXIMUM_PAPER);
		} catch (OverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Assert.assertFalse(state.outOfInk);
    	Assert.assertFalse(state.outOfPaper);
    	
    	state.addProduct(appleProduct);
    	
    	// The receipt will have 37 characters
    	state.printReceipt.setInkRemaining(37);
    	state.enablePayment();
    	state.paymentTotal = BigDecimal.valueOf(50);
    	
    	printReceipt.printReceipt();
    	Assert.assertTrue(state.lowOnInk);
    	Assert.assertTrue(state.outOfInk);
    }
}
