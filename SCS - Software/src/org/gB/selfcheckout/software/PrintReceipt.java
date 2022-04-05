package org.gB.selfcheckout.software;
import java.util.ArrayList;

import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class PrintReceipt{
    private State state; // Stores a program state for testing.
    private ItemDatabase pdc;

    public PrintReceipt(State state){
        this.state = state;
        this.pdc = state.idb;
    }
    
    public void printReceipt() {
    	ReceiptPrinter printer = state.scs.printer;
    	ArrayList<Item> scannedProducts = state.scannedItems;
    	
        for (int i = 0; i < scannedProducts.size(); i ++)
        {
        	// String desc = scannedProducts.get(i).getDescription(); // TODO: Fix this.
            String desc = "";
            for (int x = 0; x < desc.length(); x ++)
            {
            	printer.print(desc.charAt(x));
            }
            char c = '\n'; 
            printer.print(c);
            // Double price = scannedProducts.get(i).getPrice().doubleValue(); // TODO: Fix this.
            Double price = 0.0;
            String priceString = "Price: " + price;
            for (int y = 0; y < priceString.length(); y ++)
            {
            	printer.print(priceString.charAt(y));
            }
            printer.print(c);
        }
        printer.print('\n');
        String totalString = "Total:\n" + pdc.getTotalCost().doubleValue();
        for (int w = 0; w < totalString.length(); w ++) 
        {
        	printer.print(totalString.charAt(w));
        }
        printer.print('\n');
        String totalPaidString = "Total Paid:\n" + pdc.getTotalPaid().doubleValue();
        for (int z = 0; z < totalPaidString.length(); z ++)
        {
        	printer.print(totalPaidString.charAt(z));
        }
        printer.print('\n');
        Double change = pdc.getTotalPaid().subtract(pdc.getTotalCost()).doubleValue();
        String changeString = "Change:\n" + change;
        for (int q = 0; q < changeString.length(); q ++)
        {
        	printer.print(changeString.charAt(q));
        }
        
        printer.cutPaper();
    }
    
}
	