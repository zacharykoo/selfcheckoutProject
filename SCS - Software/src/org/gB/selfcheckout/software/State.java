package org.gB.selfcheckout.software;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.BanknoteDispenser;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

/**
 * Stores the state of the self-checkout program. The members scs, addBanknote,
 * addCoin, addItemToBag, checkout, and scanItem are uninitialized by default.
 */
public class State {
	// Stores all items scanned into the machine.
	public ArrayList<Item> scannedItems = new ArrayList<Item>();
	
	// We need products in order to get the price for printing the receipt
	//public ArrayList<Product> scannedProducts = new ArrayList<Product>();	
	
	// Stores the weight of all items in scannedItems.
	private double expectedWeight = 0;
	// Stores the weight at the time an item was scanned, excluding it.
	public double previousWeight = 0;
	// Stores the value of the money inserted into the system.
	public BigDecimal paymentTotal = new BigDecimal(0.0);
	// Sotres the total price of currently scanned products
	public BigDecimal totalToPay = new BigDecimal(0.0);
	
	public boolean isCardInserted = false;
	
	// Indicates if scanning/bagging or paying is enabled (the latter is true).
	public boolean paymentEnabled = false;
	// Indicates the bagging area scale should wait for the expected weight.
	public boolean waitingForBagging = false;
	// The member number of the customer.
	public String memberNumber = null;
	// The weight of a customer's personal bags.
	public double customerBagWeight = 0.0;
	
	// Stores the payments made for each card transactions.
	public ArrayList<Pair<Card.CardData, BigDecimal>> cardPayments =
		new ArrayList<Pair<Card.CardData, BigDecimal>>();
	
	// A database to contain items for purchase.
	public ItemDatabase idb = null;
	// An instance of the self checkout station hardware.
	public SelfCheckoutStation scs = null;
	// Instances of the use case handlers.
	public AddBanknote addBanknote = null;
	public AddCoin addCoin = null;
	public AddItemToBag addItemToBag = null;
	public AddCustomerBags customerBags = null;
	public PaymentMode paymentMode = null;
	public ScanItem scanItem = null;
	public ScanMembershipCard scanMembershipCard = null;
	public ReturnChange returnChange = null;
	
	boolean poweredOn = false;
	
	/**
	 * Returns the current expected weight of the scanned items list.
	 * 
	 * @return The value of expectedWeight.
	 */
	public double getExpectedWeight() {
		return expectedWeight;
	}
	
	/**
	 * Add the specified item to the scanned items list. The expected weight
	 * is updated appropriately.
	 * 
	 * @param item
	 * 		The item to be added to the scanned items list.
	 */
	public void addItem(Item item) {
		scannedItems.add(item);
		expectedWeight += item.getWeight();
	}
	
	/**
	 * Removes the specified item from the scanned items list. The expected
	 * weight is updated appropriately.
	 * 
	 * @param item
	 * 		The item to be removed from the scanned items list.
	 * @return True if the item was removed, false otherwise.
	 */
	public boolean removeItem(Item item) {
		if (scannedItems.remove(item)) {
			expectedWeight = 0.0;
			for (Item i : this.scannedItems) expectedWeight += i.getWeight();
			return true;
		} else return false;
	}
	

	/**
	 * Returns the total bag weight on the bagging 
	 * area scale.
	 * 
	 * @return The value of customerBagWeight.
	 */
	public double getExpectedBagWeight() {
		return customerBagWeight;
	}
	
	/**
	 * Add the specified bag to bagging area scale. The expected 
	 * total bag weight is updated. 
	 * 
	 * @param bag
	 * 		The bag to be added.
	 * 
	 * @return False if the scale is overloaded or payment mode is enabled or
	 * 		   an item is expected to be loaded on the scale. Otherwise returns
	 * 		   true.
	 */
	public boolean addBag(Item bag) {
		if (customerBags.overloadFlag || paymentEnabled 
				|| waitingForBagging || scs.baggingArea.isDisabled()) {
			return false;
		} else {
			customerBagWeight += bag.getWeight();
			scs.baggingArea.add(bag);
			return true;
		}
	}
	
	
	/**
	 * Removes the specified bag from the bagging area scale. The expected
	 * total bag weight is updated.
	 * 
	 * @param bag
	 * 		The bag to be removed.
	 */
	public void removeBag(Item bag) {
		customerBagWeight -= bag.getWeight();
		scs.baggingArea.remove(bag);
	}
	
	/**
	 * Enable all the devices of the self checkout station necessary for
	 * scanning and bagging items, disable all others.
	 */
	public void enableScanning() {
		scs.baggingArea.enable();
		scs.mainScanner.enable();
		scs.handheldScanner.enable();
		scs.printer.disable();
		scs.cardReader.disable();
		scs.banknoteInput.disable();
		scs.banknoteOutput.disable();
		scs.banknoteValidator.disable();
		scs.banknoteStorage.disable();
		for (Map.Entry<Integer, BanknoteDispenser> d :
        	scs.banknoteDispensers.entrySet())
    			d.getValue().disable();
		scs.coinSlot.disable();
		scs.coinTray.disable();
		scs.coinValidator.disable();
		scs.coinStorage.disable();
		for (Map.Entry<BigDecimal, CoinDispenser> d :
        	scs.coinDispensers.entrySet())
    			d.getValue().disable();
	}
	
	/**
	 * Enable all the devices of the self checkout station necessary for
	 * paying for a purchase, disable all others.
	 */
	public void enablePayment() {
		scs.baggingArea.disable();
		scs.mainScanner.disable();
		scs.handheldScanner.disable();
		scs.printer.disable();
		scs.cardReader.disable();
		scs.banknoteInput.enable();
		scs.banknoteOutput.enable();
		scs.banknoteValidator.enable();
		scs.banknoteStorage.enable();
		for (Map.Entry<Integer, BanknoteDispenser> d :
        	scs.banknoteDispensers.entrySet())
    			d.getValue().enable();
		scs.coinSlot.enable();
		scs.coinTray.enable();
		scs.coinValidator.enable();
		scs.coinStorage.enable();
		for (Map.Entry<BigDecimal, CoinDispenser> d :
        	scs.coinDispensers.entrySet())
    			d.getValue().enable();
	}
	
	/**
	 * Enable all the devices of the self checkout station necessary for
	 * processing/finalizing a purchase, disable all others.
	 */
	public void enableCheckout() {
		scs.baggingArea.enable();
		scs.mainScanner.disable();
		scs.handheldScanner.disable();
		scs.printer.enable();
		scs.cardReader.disable();
		scs.banknoteInput.disable();
		scs.banknoteOutput.disable();
		scs.banknoteValidator.disable();
		scs.banknoteStorage.disable();
		for (Map.Entry<Integer, BanknoteDispenser> d :
        	scs.banknoteDispensers.entrySet())
    			d.getValue().disable();
		scs.coinSlot.disable();
		scs.coinTray.disable();
		scs.coinValidator.disable();
		scs.coinStorage.disable();
		for (Map.Entry<BigDecimal, CoinDispenser> d :
        	scs.coinDispensers.entrySet())
    			d.getValue().disable();
	}
}
