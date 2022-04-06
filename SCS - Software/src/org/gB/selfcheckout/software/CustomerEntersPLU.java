package org.gB.selfcheckout.software;

import org.lsmr.selfcheckout.IllegalDigitException;
import org.lsmr.selfcheckout.InvalidArgumentSimulationException;
import org.lsmr.selfcheckout.NullPointerSimulationException;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

public class CustomerEntersPLU {
	public PLUCodedProduct getEnteredPLUProduct(PriceLookupCode code) {
		if (ProductDatabases.PLU_PRODUCT_DATABASE.containsKey(code)) {
			return ProductDatabases.PLU_PRODUCT_DATABASE.get(code);
		} else {
			System.out.println("Price Lookup Code matched no items in database!");
		}
		return null;
	}
}
