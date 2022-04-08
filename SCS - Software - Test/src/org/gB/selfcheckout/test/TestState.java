package org.gB.selfcheckout.test;

import java.math.BigDecimal;

import org.gB.selfcheckout.software.State;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.products.Product;

import junit.framework.Assert;

@SuppressWarnings("deprecation")

/**
 * A test suite for org.g30.selfcheckout.State.
 */
public class TestState {
	State st;
	Product i1;
	Product i2;
	
	// Create the state and test Products.
	@Before
	public void setup() throws Exception {
		st = new State();
		i1 = new Product(new BigDecimal(135.0), true) {};
		i2 = new Product(new BigDecimal(53.3), true) {};
	}
	
	// Ensure the expected weight is correctly accumulated.
	@Test
	public void testAddProduct() {
		Assert.assertEquals(st.getExpectedWeight(), 0.0);
		st.addProduct(i1);
		Assert.assertEquals(st.getExpectedWeight(), 135.0);
		st.addProduct(i2);
		Assert.assertEquals(st.getExpectedWeight(), 188.3);
	}
	
	// Ensure the expected weight is correctly deducted.
	@Test
	public void testRemoveProduct() {
		st.addProduct(i1);
		st.addProduct(i2);
		Assert.assertEquals(st.getExpectedWeight(), 188.3);
		Assert.assertEquals(st.removeProduct(i1), true);
		Assert.assertEquals(st.getExpectedWeight(), 53.3);
		Assert.assertEquals(st.removeProduct(i2), true);
		Assert.assertEquals(st.getExpectedWeight(), 0.0);
	}
	
	// Ensure the expected weight is not deducted from unnecessarily.
	@Test
	public void TestRemoveAbsentProduct() throws Exception {
		st.addProduct(i1);
		Assert.assertEquals(st.removeProduct(i2), false);
		Assert.assertEquals(st.getExpectedWeight(), 135.0);
	}
}
