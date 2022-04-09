package org.gB.selfcheckout.test;

import org.gB.selfcheckout.software.AddItemToBag;
import org.gB.selfcheckout.software.Main;
import org.gB.selfcheckout.software.State;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Item;

/**
 * A test suite for org.g30.selfcheckout.AddItemToBag.
 */
public class TestAddItemToBag {
	private State state; // Stores a program state for testing.
	
	// Initializes a state for testing with a sensible weight limits
	// an an instance of the AddItemToBage use case class.
	@Before
	public void setup() throws Exception {
		state = Main.init(10000, 10);
		AddItemToBag addItemToBag = new AddItemToBag(state);
		state.scs.baggingArea.attach(addItemToBag);
		state.addItemToBag = addItemToBag;
	}

	// Ensures that a scanned item added to the bagging scale is handled
	// correctly by the state being reset to a scannable mode.
	@Test
	public void testVaidWeightChange() {
		Item i = new Item(45.6) {};
		// "Scan" an item.
		state.addItem(i);
		state.waitingForBagging = true;
		state.scs.mainScanner.disable();
		state.scs.handheldScanner.disable();
		// Put the item on the scale.
		state.scs.baggingArea.add(i);
		// Ensure the state is correctly updated.
		Assert.assertFalse(state.waitingForBagging);
		Assert.assertFalse(state.scs.mainScanner.isDisabled());
		Assert.assertFalse(state.scs.handheldScanner.isDisabled());
	}
	
	// Ensures a disabled scale does not change the state in response to a
	// barcode scanning event.
	@Test
	public void testValidDisabledWeightChange() {
		Item i = new Item(45.6) {};
		// "Scan" an item.
		state.addItem(i);
		state.waitingForBagging = true;
		state.scs.mainScanner.disable();
		state.scs.handheldScanner.disable();
		// Disable the scale.
		state.addItemToBag.disabled(null);
		// Put the item on the scale.
		state.scs.baggingArea.add(i);
		// Ensure the state is not changed.
		Assert.assertTrue(state.waitingForBagging);
		Assert.assertTrue(state.scs.mainScanner.isDisabled());
		Assert.assertTrue(state.scs.handheldScanner.isDisabled());
	}
	
	// Ensure that placing the wrong item on the scale after scanning an item
	// does not reset the state.
	@Test
	public void testInvalidWeightChange() {
		Item i1 = new Item(45.6) {};
		Item i2 = new Item(22.3) {};
		// "Scan" item 'a'.
		state.addItem(i1);
		state.waitingForBagging = true;
		state.scs.mainScanner.disable();
		state.scs.handheldScanner.disable();
		// "Weight" item 'b'.
		state.scs.baggingArea.add(i2);
		// Ensure the state is not reversed to a scanning mode.
		Assert.assertTrue(state.waitingForBagging);
		Assert.assertTrue(state.scs.mainScanner.isDisabled());
		Assert.assertTrue(state.scs.handheldScanner.isDisabled());
	}
	
	// Ensure an unexpected item on the scale does not alter the state.
	@Test
	public void testUnexpectedChange() {
		Item i = new Item(12.66) {};
		state.scs.baggingArea.add(i);
		Assert.assertFalse(state.waitingForBagging);
		Assert.assertFalse(state.scs.mainScanner.isDisabled());
		Assert.assertFalse(state.scs.handheldScanner.isDisabled());
	}
	
	// Ensure a valid item added during an overload is correctly handled.
	@Test
	public void testValidAfterOverload() {
		Item i1 = new Item(45.6) {};
		Item i2 = new Item(20000) {};
		// "Scan" an item.
		state.addItem(i1);
		state.waitingForBagging = true;
		state.scs.mainScanner.disable();
		state.scs.handheldScanner.disable();
		// Put a spurious overloading item on the scale.
		state.scs.baggingArea.add(i2);
		Assert.assertTrue(state.waitingForBagging);
		Assert.assertTrue(state.scs.mainScanner.isDisabled());
		Assert.assertTrue(state.scs.handheldScanner.isDisabled());
		state.scs.baggingArea.add(i1); // Add the scanned item.
		Assert.assertTrue(state.waitingForBagging);
		Assert.assertTrue(state.scs.mainScanner.isDisabled());
		Assert.assertTrue(state.scs.handheldScanner.isDisabled());
		state.scs.baggingArea.remove(i2); // Remove the spurious item.
		// Ensure the state is correctly updated.
		Assert.assertFalse(state.waitingForBagging);
		Assert.assertFalse(state.scs.mainScanner.isDisabled());
		Assert.assertFalse(state.scs.handheldScanner.isDisabled());
	}
	
	// Ensure a spurious overloading item does not change the state when put on
	// the scale.
	@Test
	public void overloadCycle() {
		Item i = new Item(20000) {};
		state.scs.baggingArea.add(i);
		Assert.assertFalse(state.waitingForBagging);
		Assert.assertFalse(state.scs.mainScanner.isDisabled());
		Assert.assertFalse(state.scs.handheldScanner.isDisabled());
		state.scs.baggingArea.remove(i);
		Assert.assertFalse(state.waitingForBagging);
		Assert.assertFalse(state.scs.mainScanner.isDisabled());
		Assert.assertFalse(state.scs.handheldScanner.isDisabled());
	}
	
	// Ensure the state is not updated if the scale is disabled while waiting
	// an item.
	@Test
	public void addItemWhileDisabled() {
		Item i = new Item(45.6) {};
		state.scs.baggingArea.disable(); // Disable the scale.
		// "Scan" an item.
		state.addItem(i);
		state.waitingForBagging = true;
		state.scs.mainScanner.disable();
		state.scs.handheldScanner.disable();
		state.scs.baggingArea.add(i);
		Assert.assertTrue(state.waitingForBagging);
		Assert.assertTrue(state.scs.mainScanner.isDisabled());
		Assert.assertTrue(state.scs.handheldScanner.isDisabled());
	}
}
