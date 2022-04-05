package org.gB.selfcheckout.test;

import org.gB.selfcheckout.software.ItemDatabase;
import org.gB.selfcheckout.software.Main;
import org.gB.selfcheckout.software.Pair;
import org.gB.selfcheckout.software.PayWithCard;
import org.gB.selfcheckout.software.ScanItem;
import org.gB.selfcheckout.software.State;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.CardReaderObserver;
import org.lsmr.selfcheckout.devices.observers.ElectronicScaleObserver;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.Product;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;

public class TestPayWithCard {

    private State state;
    private CardReader cardReader;
    private Card validTestCard1;
    private Card validTestCard2;
    private Card invalidTestCard;
    private PayWithCard payWithCard;
    private BigDecimal amountToPay;
    private BigDecimal subtractedAmountLeft;

    @Before
    public void setup() {
        //Initialize State.
        try {
        	state = Main.init(100, 1);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        //Set variables to respective components of State.
        assert state != null;

        cardReader = state.scs.cardReader;
        cardReader.endConfigurationPhase();        
        
        // Create test credit cards
        validTestCard1 = new Card("VISA", "4520123412341234", "John Doe", "123", "9999", true, true);
        validTestCard2 = new Card("MASTERCARD", "8976650412338276", "Jeff MacDonald", "344", "8923", true, true);
        
        // Create an test card for invalid attempts, with no chip and no tap enabled
        invalidTestCard = new Card("MASTERCARD", "7822123412349999", "John Doe", "842", "7790", false, false);
        
        //Initialize test class
        amountToPay = new BigDecimal(13.75);
        
        BigDecimal tTP  = new BigDecimal(55.15);
        state.totalToPay = tTP;
        subtractedAmountLeft = new BigDecimal(55.15-13.75);
        
    }

    
    @Test
    public void testAddsCorrectValue() {
    	payWithCard = new PayWithCard(state, amountToPay);
        cardReader.attach(payWithCard);
    	while (true) {
    		try {
            	cardReader.swipe(validTestCard1);
            	break;
    		} catch (IOException e) {
    			// Keep trying
    		}
    	}
    	Assert.assertEquals(state.paymentTotal, amountToPay);
    	state.cardPayments.clear();
    	state.paymentTotal = new BigDecimal(0);
    }
    
    @Test
    public void testAmountToPayTooHigh() {
    	BigDecimal highAmountToPay = new BigDecimal(60.25);
    	payWithCard = new PayWithCard(state, highAmountToPay);
        cardReader.attach(payWithCard);
    	while (true) {
    		try {
            	cardReader.swipe(validTestCard1);
            	break;
    		} catch (IOException e) {
    			// Keep trying
    		}
    	}
    	payWithCard.amountToPay = amountToPay;
    	// Paid exactly the price of the products scanned, not more
    	Assert.assertEquals(state.paymentTotal, state.totalToPay);
    	state.cardPayments.clear();
    	state.paymentTotal = new BigDecimal(0);
    }
    
    @Test
    public void testStoresSingleTapCardData() throws IOException {
    	payWithCard = new PayWithCard(state, amountToPay);
        cardReader.attach(payWithCard);
    	while (true) {
    		try {
            	cardReader.tap(validTestCard1);
            	break;
    		} catch (IOException e) {
    			// Keep trying
    		}
    	}
    	// Check that stored card is correct
    	Pair<CardData, BigDecimal> storedCardPair = state.cardPayments.get(0);
    	Assert.assertEquals(storedCardPair.first.getNumber(), "4520123412341234");
    	Assert.assertEquals(storedCardPair.first.getCVV(), "123");
    	Assert.assertEquals(storedCardPair.first.getCardholder(), "John Doe");
    	Assert.assertEquals(storedCardPair.first.getType(), "VISA");
    	Assert.assertEquals(storedCardPair.second, amountToPay);
    	state.cardPayments.clear();
    	state.paymentTotal = new BigDecimal(0);
    }
    
    @Test
    public void testStoresMultipleTapCardData() throws IOException {
    	payWithCard = new PayWithCard(state, amountToPay);
        cardReader.attach(payWithCard);
    	// Tap once
    	while (true) {
    		try {
            	cardReader.tap(validTestCard1);
            	break;
    		} catch (IOException e) {
    			// Keep trying
    		}
    	}
    	// Tap twice
    	while (true) {
    		try {
            	cardReader.tap(validTestCard1);
            	break;
    		} catch (IOException e) {
    			// Keep trying
    		}
    	}
    	Pair<CardData, BigDecimal> storedCardPair = state.cardPayments.get(0);
    	Assert.assertEquals(state.cardPayments.size(), 1);
    	Assert.assertEquals(storedCardPair.first.getNumber(), "4520123412341234");
    	Assert.assertEquals(storedCardPair.first.getCVV(), "123");
    	Assert.assertEquals(storedCardPair.first.getCardholder(), "John Doe");
    	Assert.assertEquals(storedCardPair.first.getType(), "VISA");
    	Assert.assertEquals(storedCardPair.second, amountToPay.add(amountToPay));
    	state.cardPayments.clear();
    	state.paymentTotal = new BigDecimal(0);
    }
    
    @Test
    public void testPayWithTwoCards() {
    	payWithCard = new PayWithCard(state, amountToPay);
        cardReader.attach(payWithCard);
    	// Tap once
    	while (true) {
    		try {
            	cardReader.tap(validTestCard1);
            	break;
    		} catch (IOException e) {
    			// Keep trying
    		}
    	}
    	// Tap twice
    	while (true) {
    		try {
            	cardReader.tap(validTestCard2);
            	break;
    		} catch (IOException e) {
    			// Keep trying
    		}
    	}
    	Assert.assertEquals(state.cardPayments.size(), 2);

    	Pair<CardData, BigDecimal> storedCardPair1 = state.cardPayments.get(0);
    	Assert.assertEquals(storedCardPair1.first.getNumber(), "4520123412341234");
    	Assert.assertEquals(storedCardPair1.first.getCVV(), "123");
    	Assert.assertEquals(storedCardPair1.first.getCardholder(), "John Doe");
    	Assert.assertEquals(storedCardPair1.first.getType(), "VISA");
    	Assert.assertEquals(storedCardPair1.second, amountToPay);
    	
    	Pair<CardData, BigDecimal> storedCardPair2 = state.cardPayments.get(1);
    	Assert.assertEquals(storedCardPair2.first.getNumber(), "8976650412338276");
    	Assert.assertEquals(storedCardPair2.first.getCVV(), "344");
    	Assert.assertEquals(storedCardPair2.first.getCardholder(), "Jeff MacDonald");
    	Assert.assertEquals(storedCardPair2.first.getType(), "MASTERCARD");
    	Assert.assertEquals(storedCardPair2.second, amountToPay);

    	state.cardPayments.clear();
    	state.paymentTotal = new BigDecimal(0);
    }

    @Test
    public void testEnabled() {
    	payWithCard = new PayWithCard(state, amountToPay);
        cardReader.attach(payWithCard);
        payWithCard.enabled(cardReader);
        Assert.assertTrue(payWithCard.isEnabled);
    }
    
    @Test
    public void testDisabled() {
    	payWithCard = new PayWithCard(state, amountToPay);
        cardReader.attach(payWithCard);
        payWithCard.enabled(cardReader);
        payWithCard.disabled(state.scs.cardReader);
        Assert.assertFalse(payWithCard.isEnabled);
    }

    @Test
    public void testCardInserted() {
    	payWithCard = new PayWithCard(state, amountToPay);
        cardReader.attach(payWithCard);
    	// Insert
    	while (true) {
    		try {
            	cardReader.insert(validTestCard1, "9999");
            	break;
    		} catch (IOException e) {
    			// Keep trying
    		}
    	}
        Assert.assertTrue(state.isCardInserted);
        state.cardPayments.clear();
        state.paymentTotal = new BigDecimal(0);
    }

    @Test
    public void testCardRemoved() {
    	payWithCard = new PayWithCard(state, amountToPay);
        cardReader.attach(payWithCard);
    	// Insert
    	while (true) {
    		try {
            	cardReader.insert(validTestCard1, "9999");
            	break;
    		} catch (IOException e) {
    			// Keep trying
    		}
    	}
    	// Remove
    	while (true) {
    		try {
            	cardReader.remove();
            	break;
    		} catch (Exception e) {
    			// Keep trying
    		}
    	}
        Assert.assertFalse(state.isCardInserted);
        state.cardPayments.clear();
        state.paymentTotal = new BigDecimal(0);
    }
}
