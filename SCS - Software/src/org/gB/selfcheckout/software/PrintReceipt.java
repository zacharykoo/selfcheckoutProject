package org.gB.selfcheckout.software;
import java.util.ArrayList;

import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.EmptyException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class PrintReceipt{
    private State state; // Stores a program state for testing.
    private ItemDatabase pdc;

    public PrintReceipt(State state){
        this.state = state;
        this.pdc = state.idb;
    }
    
    private void printLetter(ReceiptPrinter printer, char letter) {
    	try {
			printer.print(letter);
		} catch (EmptyException | OverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
            	printLetter(printer, desc.charAt(x));
            }
            char c = '\n'; 
            printLetter(printer, c);
            // Double price = scannedProducts.get(i).getPrice().doubleValue(); // TODO: Fix this.
            Double price = 0.0;
            String priceString = "Price: " + price;
            for (int y = 0; y < priceString.length(); y ++)
            {
            	printLetter(printer, priceString.charAt(y));
            }
            printLetter(printer, c);
        }
        printLetter(printer, '\n');
        String totalString = "Total:\n" + pdc.getTotalCost().doubleValue();
        for (int w = 0; w < totalString.length(); w ++) 
        {
        	printLetter(printer, totalString.charAt(w));
        }
        printLetter(printer, '\n');
        String totalPaidString = "Total Paid:\n" + pdc.getTotalPaid().doubleValue();
        for (int z = 0; z < totalPaidString.length(); z ++)
        {
        	printLetter(printer, totalPaidString.charAt(z));
        }
        printLetter(printer, '\n');
        Double change = pdc.getTotalPaid().subtract(pdc.getTotalCost()).doubleValue();
        String changeString = "Change:\n" + change;
        for (int q = 0; q < changeString.length(); q ++)
        {
        	printLetter(printer, changeString.charAt(q));
        }
        
        printer.cutPaper();
    }
    
}
	