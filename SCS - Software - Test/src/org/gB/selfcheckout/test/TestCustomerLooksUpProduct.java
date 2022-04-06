package org.gB.selfcheckout.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.gB.selfcheckout.software.CustomerLooksUpProduct;
import org.junit.Test;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

public class TestCustomerLooksUpProduct {

	@Test
	public void LookUpOnEmptyPLUDatabase() {
		PriceLookupCode code = new PriceLookupCode("12345");
		CustomerLooksUpProduct process = new CustomerLooksUpProduct();
		assertEquals(null, process.customerLooksUpPLUProduct(code));
	}
	
	@Test
	public void LookUpOnNonEmptyWrongPLUDatabase() {
		ProductDatabases.PLU_PRODUCT_DATABASE.clear();
		PriceLookupCode dataCode = new PriceLookupCode("00000");
		PriceLookupCode wrongCode = new PriceLookupCode("12345");
		PLUCodedProduct product = new PLUCodedProduct(dataCode, "description", new BigDecimal(1.0));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(product.getPLUCode(), product);
		
		CustomerLooksUpProduct process = new CustomerLooksUpProduct();
		assertEquals(null, process.customerLooksUpPLUProduct(wrongCode));
	}

	@Test
	public void LookUpOnNonEmptyCorrectPLUDatabase() {
		ProductDatabases.PLU_PRODUCT_DATABASE.clear();
		PriceLookupCode dataCode = new PriceLookupCode("12345");
		PriceLookupCode wrongCode = new PriceLookupCode("12345");
		PLUCodedProduct product = new PLUCodedProduct(dataCode, "description", new BigDecimal(1.0));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(product.getPLUCode(), product);
		
		CustomerLooksUpProduct process = new CustomerLooksUpProduct();
		assertEquals(product, process.customerLooksUpPLUProduct(wrongCode));
	}
}
