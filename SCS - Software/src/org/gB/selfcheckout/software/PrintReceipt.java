package org.gB.selfcheckout.software;
import java.math.BigDecimal;
import java.util.Map;

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
	private Map<Product, Integer> products;

    public PrintReceipt(State state) {
        this.state = state;
        this.pdc = state.idb;
		this.printer = state.scs.printer;
		this.enabled = true;
		this.products = state.productCart;
    }
    
    private void printLetter(char letter) {
    	try {
			printer.print(letter);
			if (letter == '\n' && !state.outOfPaper) {
				useLineOfPaper();
			} else if (letter == ' '){
				// ignore
			} else if (!state.outOfInk){
				useInk();
			}
		} catch (EmptyException | OverloadException e) {
			Main.error("Printer is out of ink and/or paper.");
		}
    }
    
    public void printReceipt() {
    	String header = String.format("%-4s %-30s %-7s %-5s\n", "Qty", "Product", "Price", "Total");
    	String desc = header;
    	// Printing quantity, product, price per product, total for that product
    	for (Map.Entry<Product, Integer> pair : products.entrySet()) {
    		
    		Product product = pair.getKey();
    		Integer qty = pair.getValue();
    		
    		String prod;
    		String price;
    		String total;
    		
    		if (product instanceof BarcodedProduct) {
    			prod = ((BarcodedProduct) product).getDescription();
    		} else if (product instanceof PLUCodedProduct) {
        		prod = ((PLUCodedProduct) product).getDescription();
    		} else {
    			prod = "";
    		}
    		price = product.getPrice().toString();
    		total = (product.getPrice().multiply(BigDecimal.valueOf(qty))).toString();
    		
    		desc += String.format("%-4d %-30s $ %-5s $ %-5s\n", qty, prod, price, total);
    	}
    	
    	for (int i = 0; i < desc.length(); i++) {
			printLetter(desc.charAt(i));
		}
    	
    	// Print total price for all products
    	String totalString = "\nTotal Cost: $" + state.totalToPay.doubleValue() + "\n";
        for (int w = 0; w < totalString.length(); w ++) 
        {
        	printLetter(totalString.charAt(w));
        }
    	
    	// Print total price paid
        String totalPaidString = "Total Paid: $" + state.paymentTotal.doubleValue() + "\n";
        for (int z = 0; z < totalPaidString.length(); z ++)
        {
        	printLetter(totalPaidString.charAt(z));
        }
    	
    	// Print change due
        Double change = state.paymentTotal.doubleValue() - state.totalToPay.doubleValue();
        /*pdc.getTotalPaid().subtract(pdc.getTotalCost()).doubleValue(); */
        String changeString = "Change due: $" + change + "\n";
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
		charactersOfInkRemaining--;
		if (charactersOfInkRemaining <= ReceiptPrinter.MAXIMUM_INK / 10) {
			this.state.lowOnInk = true;
		}
		if (charactersOfInkRemaining == 0) {
			this.state.outOfInk = true;
		}
	}
	
	public void useLineOfPaper() {
		linesOfPaperRemaining--;
		if (linesOfPaperRemaining <= ReceiptPrinter.MAXIMUM_PAPER / 10) {
			this.state.lowOnPaper = true;
		}
		if (linesOfPaperRemaining == 0) {
			this.state.outOfPaper = true;
		}
	}
	
	// For testing purposes
	public void setPaperRemaining(int paper) {
		linesOfPaperRemaining = paper;
	}
	
	public void setInkRemaining(int ink) {
		charactersOfInkRemaining = ink;
	}
}
	