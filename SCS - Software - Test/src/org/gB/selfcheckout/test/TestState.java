package org.gB.selfcheckout.test;

import org.gB.selfcheckout.software.State;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Item;

import junit.framework.Assert;

@SuppressWarnings("deprecation")

/**
 * A test suite for org.g30.selfcheckout.State.
 */
public class TestState {
	State st;
	Item i1;
	Item i2;
	
	// Create the state and test items.
	@Before
	public void setup() throws Exception {
		st = new State();
		i1 = new Item(135.0) {};
		i2 = new Item(53.3) {};
	}
	
	// Ensure the expected weight is correctly accumulated.
	@Test
	public void testAddItem() {
		Assert.assertEquals(st.getExpectedWeight(), 0.0);
		st.addItem(i1);
		Assert.assertEquals(st.getExpectedWeight(), 135.0);
		st.addItem(i2);
		Assert.assertEquals(st.getExpectedWeight(), 188.3);
	}
	
	// Ensure the expected weight is correctly deducted.
	@Test
	public void testRemoveItem() {
		st.addItem(i1);
		st.addItem(i2);
		Assert.assertEquals(st.getExpectedWeight(), 188.3);
		Assert.assertEquals(st.removeItem(i1), true);
		Assert.assertEquals(st.getExpectedWeight(), 53.3);
		Assert.assertEquals(st.removeItem(i2), true);
		Assert.assertEquals(st.getExpectedWeight(), 0.0);
	}
	
	// Ensure the expected weight is not deducted from unnecessarily.
	@Test
	public void TestRemoveAbsentItem() throws Exception {
		st.addItem(i1);
		Assert.assertEquals(st.removeItem(i2), false);
		Assert.assertEquals(st.getExpectedWeight(), 135.0);
	}
}
