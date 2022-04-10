package org.gB.selfcheckout.test;
import org.gB.selfcheckout.software.CustomerDoesNotWantToBagScannedItem;
import org.gB.selfcheckout.software.Main;
import org.gB.selfcheckout.software.State;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Item;

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
		Item i = new Item(25.7) {};
		// "Scan" an item.
		state.addItem(i);
		state.waitingForBagging = false;
		state.scs.mainScanner.disable();
		state.scs.handheldScanner.disable();
		// Put the item on the scale.
		state.scs.baggingArea.add(i);
		// Ensure the state is correctly updated.
		Assert.assertFalse(state.scs.mainScanner.isDisabled());
		Assert.assertFalse(state.scs.handheldScanner.isDisabled());
	}
	
	@Test
	public void testValidDisabledWeightChange() {
		Item i = new Item(45.6) {};
		// "Scan" an item.
		state.addItem(i);
		state.waitingForBagging = false;
		state.scs.mainScanner.disable();
		state.scs.handheldScanner.disable();
		// Disable the scale.
		state.customerDoesNotWantToBagScannedItem.disabled(null);
		// Put the item on the scale.
		state.scs.baggingArea.add(i);
		// Ensure the state is not changed.
		Assert.assertTrue(state.scs.mainScanner.isDisabled());
		Assert.assertTrue(state.scs.handheldScanner.isDisabled());
	}
	
	@Test
	public void testInvalidWeightChange() {
		Item i1 = new Item(45.6) {};
		Item i2 = new Item(22.3) {};
		// "Scan" item 'a'.
		state.addItem(i1);
		state.scs.mainScanner.disable();
		state.scs.handheldScanner.disable();
		// "Weight" item 'b'.
		state.scs.baggingArea.add(i2);
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
		Item i1 = new Item(45.6) {};
		Item i2 = new Item(20000) {};
		// "Scan" an item.
		state.addItem(i1);
		
		state.scs.mainScanner.disable();
		state.scs.handheldScanner.disable();
		// Put a spurious overloading item on the scale.
		state.scs.baggingArea.add(i2);
		
		Assert.assertTrue(state.scs.mainScanner.isDisabled());
		Assert.assertTrue(state.scs.handheldScanner.isDisabled());
		state.scs.baggingArea.add(i1); // Add the scanned item.
		
		Assert.assertTrue(state.scs.mainScanner.isDisabled());
		Assert.assertTrue(state.scs.handheldScanner.isDisabled());
		state.scs.baggingArea.remove(i2); // Remove the spurious item.
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
	
	public void addItemWhileDisabled() {
		Item i = new Item(45.6) {};
		state.scs.baggingArea.disable(); // Disable the scale.
		// "Scan" an item.
		state.addItem(i);

		state.scs.mainScanner.disable();
		state.scs.handheldScanner.disable();
		state.scs.baggingArea.add(i);
		
		Assert.assertTrue(state.scs.mainScanner.isDisabled());
		Assert.assertTrue(state.scs.handheldScanner.isDisabled());
	}
	
	@Test
	public void testEnabled() {
		state.customerDoesNotWantToBagScannedItem.enabled(null);
		Assert.assertTrue(state.scs.baggingArea.isDisabled());
	}
	
	@Test
	public void testNotEnabled() {
		state.customerDoesNotWantToBagScannedItem.disabled(null);
		Assert.assertFalse(state.scs.baggingArea.isDisabled());
	}
	
	
}
