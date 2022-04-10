package org.gB.selfcheckout.test;
import java.math.BigDecimal;

import org.gB.selfcheckout.software.CustomerDoesNotWantToBagScannedItem;
import org.gB.selfcheckout.software.Main;
import org.gB.selfcheckout.software.State;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.products.Product;

public class TestCustomerDoesNotWantToBagScannedItem {
	private State state;
	
	
	@Before
	public void setup() throws Exception {
		state = Main.init(10000, 10);
		CustomerDoesNotWantToBagScannedItem customerDoesNotBagScannedItem = new CustomerDoesNotWantToBagScannedItem(state);
		state.scs.baggingArea.attach(customerDoesNotBagScannedItem);
		state.customerDoesNotWantToBagScannedItem = customerDoesNotBagScannedItem;
	}
	
	@Test
	public void testValidWeightChange() {
		state.customerDoesNotWantToBagScannedItem.enabled(null);
		//Make sure the scale is enabled.
		//Product i = new Product(new BigDecimal(25.7), true) {};
		Item a = new Item(25.7) {};
		// "Scan" an item.
		//state.addProduct(i);
		state.waitingForBagging = false;
		state.scs.mainScanner.disable();
		state.scs.handheldScanner.disable();
		// Put the item on the scale.
		state.scs.baggingArea.add(a);
		// Ensure the state is correctly updated.
		Assert.assertFalse(state.scs.mainScanner.isDisabled());
		Assert.assertFalse(state.scs.handheldScanner.isDisabled());
	}
	
	@Test
	public void testValidWeightChangeButBaggingExpected() {
		//Product i = new Product(new BigDecimal(45.6), true) {};
		Item a = new Item(25.7) {};
		// "Scan" an item.
		//state.addProduct(i);
		state.waitingForBagging = true;
		state.scs.mainScanner.disable();
		state.scs.handheldScanner.disable();
		// Put the item on the scale.
		state.scs.baggingArea.add(a);
		// Ensure the state is correctly updated.
	}
	
	@Test
	public void testValidDisabledWeightChange() {
		//Product i = new Product(new BigDecimal(45.6), true) {};
		Item a = new Item(45.6) {};
		// "Scan" an item.
		//state.addProduct(i);
		state.waitingForBagging = false;
		state.scs.mainScanner.disable();
		state.scs.handheldScanner.disable();
		// Disable the scale.
		state.customerDoesNotWantToBagScannedItem.disabled(null);
		// Put the item on the scale.
		state.scs.baggingArea.add(a);
		// Ensure the state is not changed.
		Assert.assertTrue(state.scs.mainScanner.isDisabled());
		Assert.assertTrue(state.scs.handheldScanner.isDisabled());
	}
	
	@Test
	public void testInvalidWeightChange() {
		//Product i1 = new Product(new BigDecimal(45.6), true) {};
		Item a2 = new Item(22.3) {};
		// "Scan" item 'a'.
		//state.addProduct(i1);
		state.scs.mainScanner.disable();
		state.scs.handheldScanner.disable();
		// "Weight" item 'b'.
		state.scs.baggingArea.add(a2);
		// Ensure the state is not reversed to a scanning mode.
		Assert.assertTrue(state.scs.mainScanner.isDisabled());
		Assert.assertTrue(state.scs.handheldScanner.isDisabled());
	}
	
	@Test
	public void testUnexpectedChange() {
		Item i = new Item(12.66) {};
		state.scs.baggingArea.add(i);
		Assert.assertFalse(state.scs.mainScanner.isDisabled());
		Assert.assertFalse(state.scs.handheldScanner.isDisabled());
	}
	
	@Test
	public void testValidAfterOverload() {
		//Product i1 = new Product(new BigDecimal(45.6), true) {};
		Item a1 = new Item(45.6) {};
		Item a2 = new Item(20000) {};
		// "Scan" an item.
		//state.addProduct(i1);
		
		state.scs.mainScanner.disable();
		state.scs.handheldScanner.disable();
		// Put a spurious overloading item on the scale.
		state.scs.baggingArea.add(a2);
		
		Assert.assertTrue(state.scs.mainScanner.isDisabled());
		Assert.assertTrue(state.scs.handheldScanner.isDisabled());
		state.scs.baggingArea.add(a1); // Add the scanned item.
		
		Assert.assertTrue(state.scs.mainScanner.isDisabled());
		Assert.assertTrue(state.scs.handheldScanner.isDisabled());
		state.scs.baggingArea.remove(a2); // Remove the spurious item.
		// Ensure the state is correctly updated.
		
		Assert.assertFalse(state.scs.mainScanner.isDisabled());
		Assert.assertFalse(state.scs.handheldScanner.isDisabled());
	}
	
	
	@Test
	public void overloadCycle() {
		Item i = new Item(20000) {};
		state.scs.baggingArea.add(i);
		Assert.assertFalse(state.scs.mainScanner.isDisabled());
		Assert.assertFalse(state.scs.handheldScanner.isDisabled());
		state.scs.baggingArea.remove(i);
		Assert.assertFalse(state.waitingForBagging);
		Assert.assertFalse(state.scs.mainScanner.isDisabled());
		Assert.assertFalse(state.scs.handheldScanner.isDisabled());
	}
	
	@Test
	public void overloadCycleButDisabled() {
		state.customerDoesNotWantToBagScannedItem.disabled(null);
		Item i = new Item(20000) {};
		state.scs.baggingArea.add(i);
		Assert.assertFalse(state.scs.mainScanner.isDisabled());
		Assert.assertFalse(state.scs.handheldScanner.isDisabled());
		state.scs.baggingArea.remove(i);
		Assert.assertFalse(state.waitingForBagging);
		Assert.assertFalse(state.scs.mainScanner.isDisabled());
		Assert.assertFalse(state.scs.handheldScanner.isDisabled());
	}
	
	public void addItemWhileDisabled() {
		//Product i = new Product(new BigDecimal(45.6), true) {};
		Item a = new Item(45.6) {};
		state.scs.baggingArea.disable(); // Disable the scale.
		// "Scan" an item.
		//state.addProduct(i);

		state.scs.mainScanner.disable();
		state.scs.handheldScanner.disable();
		state.scs.baggingArea.add(a);
		
		Assert.assertTrue(state.scs.mainScanner.isDisabled());
		Assert.assertTrue(state.scs.handheldScanner.isDisabled());
	}
	
	
	
}
