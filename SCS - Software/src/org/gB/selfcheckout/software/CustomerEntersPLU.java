package org.gB.selfcheckout.software;

import java.util.ArrayList;
import java.util.Set;
import java.util.Map.Entry;

import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

public class CustomerEntersPLU {
	/**
	 * Looks up the PLU code in the database and returns the product when entered correctly.
	 * 
	 * @param The PLU code to look up.
	 * @return 
	 * 
	 **/
	public PLUCodedProduct getEnteredPLUProduct(PriceLookupCode code) {
		if (ProductDatabases.PLU_PRODUCT_DATABASE.containsKey(code)) {
			return ProductDatabases.PLU_PRODUCT_DATABASE.get(code);
		} else {
			Main.error("Price Lookup Code matched no items in database!");
		}
		return null;
	}
}
