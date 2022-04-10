package org.gB.selfcheckout.software;
import java.util.ArrayList;

import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.EmptyException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.ReceiptPrinterObserver;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;
import org.lsmr.selfcheckout.products.Product;

public class PrintReceipt implements ReceiptPrinterObserver {
    private State state; // Stores a program state for testing.
    private boolean enabled; // Indicates whether the watched device is enabled.
    private ItemDatabase pdc;
	private ReceiptPrinter printer;
	private int charactersOfInkRemaining = 0;
	private int linesOfPaperRemaining = 0;

    public PrintReceipt(State state){
        this.state = state;
        this.pdc = state.idb;
		this.printer = state.scs.printer;
		this.enabled = true;
    }
    
    private void printLetter(char letter) {
    	try {
			printer.print(letter);
			if (letter == '\n') {
				useLineOfPaper();
			} else {
				useInk();
			}
		} catch (EmptyException | OverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void printReceipt() {
    	ArrayList<Product> scannedProducts = pdc.getScannedProducts();
    	
        for (int i = 0; i < scannedProducts.size(); i ++)
        {
        	String desc;
        	Product product = scannedProducts.get(i);
        	if (product instanceof BarcodedProduct) {
        		desc = ((BarcodedProduct) product).getDescription();
        	} else if (product instanceof PLUCodedProduct) {
        		desc = ((PLUCodedProduct) product).getDescription();
        	} else {
        		desc = "";
        	}
        	
            for (int x = 0; x < desc.length(); x ++)
            {
            	printLetter(desc.charAt(x));
            }
            char c = '\n'; 
            printLetter(c);
            Double price = scannedProducts.get(i).getPrice().doubleValue();
            String priceString = "Price: " + price;
            for (int y = 0; y < priceString.length(); y ++)
            {
            	printLetter(priceString.charAt(y));
            }
            printLetter(c);
        }
        printLetter('\n');
        
        String totalString = "Total:\n" + pdc.getTotalCost().doubleValue();
        for (int w = 0; w < totalString.length(); w ++) 
        {
        	printLetter(totalString.charAt(w));
        }
        printLetter('\n');
        String totalPaidString = "Total Paid:\n" + pdc.getTotalPaid().doubleValue();
        for (int z = 0; z < totalPaidString.length(); z ++)
        {
        	printLetter(totalPaidString.charAt(z));
        }
        printLetter('\n');
        Double change = pdc.getTotalPaid().subtract(pdc.getTotalCost()).doubleValue();
        String changeString = "Change:\n" + change;
        for (int q = 0; q < changeString.length(); q ++)
        {
        	printLetter(changeString.charAt(q));
        }
        
        printer.cutPaper();
    }

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		this.enabled = true;		
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		this.enabled = false;		
	}

	@Override
	public void outOfPaper(ReceiptPrinter printer) {
		this.linesOfPaperRemaining = 0;
	}

	@Override
	public void outOfInk(ReceiptPrinter printer) {
		this.charactersOfInkRemaining = 0;
		
	}

	@Override
	public void paperAdded(ReceiptPrinter printer) {
		// Assumption: a new full roll is added each time
		this.linesOfPaperRemaining = ReceiptPrinter.MAXIMUM_PAPER;
		this.state.lowOnPaper = false;
		this.state.outOfPaper = false;		
	}

	@Override
	public void inkAdded(ReceiptPrinter printer) {
		// Assumption: Ink is filled to full each time
		this.charactersOfInkRemaining = ReceiptPrinter.MAXIMUM_INK;
		this.state.lowOnInk = false;
		this.state.outOfInk = false;
	}
	
	public void useInk() {
		this.charactersOfInkRemaining--;
		if (charactersOfInkRemaining == ReceiptPrinter.MAXIMUM_INK / 10) {
			this.state.lowOnInk = true;
		} else if (charactersOfInkRemaining == 0) {
			this.state.outOfInk = true;
		}
	}
	
	public void useLineOfPaper() {
		this.linesOfPaperRemaining--;
		if (linesOfPaperRemaining == ReceiptPrinter.MAXIMUM_PAPER / 10) {
			this.state.lowOnPaper = true;
		} else if (charactersOfInkRemaining == 0) {
			this.state.outOfPaper = true;
		}
	}
}
	