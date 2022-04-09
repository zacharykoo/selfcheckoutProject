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

}
