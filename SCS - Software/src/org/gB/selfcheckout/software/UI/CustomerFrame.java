package org.gB.selfcheckout.software.UI;

import java.awt.CardLayout;

import javax.swing.JFrame;

/**
 * JFrame to contain the UI used by customers at self-checkout stations.
 */
public class CustomerFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public CardLayout cardLayout = new CardLayout();
	CustomerScanItem scanItem;
//	CustomerProductLookup lookup;
	CustomerWaitingToBag waitToBag;
	ProceedToPayment proceedToPay;
	PayWithCard payWithCard;
	PayWithCash payWithCash;
//	CustomerEnterMember enterMember;
	
	public CustomerFrame(int stationIndex) {
		super("Self-Checkout Station: " + Integer.toString(stationIndex + 1));
//		cardLayout.addLayoutComponent("lookup", lookup);
		cardLayout.addLayoutComponent("scanItem", scanItem);
		cardLayout.addLayoutComponent("waitToBag", waitToBag);
		cardLayout.addLayoutComponent("proceedToPay", proceedToPay);
		cardLayout.addLayoutComponent("payWithCard", payWithCard);
		cardLayout.addLayoutComponent("payWithCash", payWithCash);
//		cardLayout.addLayoutComponent("enterMember", enterMember);
		cardLayout.show(this.getRootPane(), "scanItem");
		this.setLayout(cardLayout);
		this.setSize(1280, 720);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void waitingToBag() {
		cardLayout.show(this.getRootPane(), "waitToBag");
		waitToBag.waiting();
	}
}
