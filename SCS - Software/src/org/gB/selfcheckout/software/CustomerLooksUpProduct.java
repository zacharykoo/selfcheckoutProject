package org.gB.selfcheckout.software;
import org.lsmr.selfcheckout.InvalidArgumentSimulationException;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

//The goal here is to find a product given its lookup code
public class CustomerLooksUpProduct  {
	ArrayList<PLUCodedProduct> productList = new ArrayList<PLUCodedProduct>();
	
	public ArrayList<PLUCodedProduct> customerLooksUpPLUProduct(String partialLookUpCode) {
		boolean track = true;
		char[] charArray = partialLookUpCode.toCharArray();
		Numeral[] numerals = new Numeral[charArray.length];
		Set<PriceLookupCode> keys = ProductDatabases.PLU_PRODUCT_DATABASE.keySet();
		for(PriceLookupCode key : keys) {
			for(int i = 0; i < partialLookUpCode.length(); i++) {
				if(key.getNumeralAt(i) != numerals[i]) {
					track = false;
				}
			}
			if(track == true) {
				productList.add(ProductDatabases.PLU_PRODUCT_DATABASE.get(key));
			}
		}
		return productList;
	}
}
