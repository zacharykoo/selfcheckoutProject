package org.gB.selfcheckout.software.UI;

import javax.swing.JFrame;

/**
 * JFrame to contain the UI used by customers at self-checkout stations.
 */
public class CustomerFrame extends JFrame {
	private static final long serialVersionUID = 1L;
//	CustomerScanAndBag scanAndBag;
//	CustomerProductLookup lookup;
	CustomerWaitingToBag waitToBag;
//	CustomerPayment payment;
//	CustomerEnterMember enterMember;
	
	public CustomerFrame(int stationIndex) {
		super("Self-Checkout Station: " + Integer.toString(stationIndex + 1));
		this.add(waitToBag); //this.add(scanAndBag);
		this.setSize(1280, 720);
		this.setVisible(true);
	}
	
	public void waitingToBag() {
		this.removeAll();
		this.add(waitToBag);
		waitToBag.waiting();
	}
}
