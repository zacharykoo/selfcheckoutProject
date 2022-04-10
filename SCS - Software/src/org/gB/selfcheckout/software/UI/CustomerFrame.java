package org.gB.selfcheckout.software.UI;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * JFrame to contain the UI used by customers at self-checkout stations.
 */
public class CustomerFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public CardLayout cardLayout = new CardLayout();
	public int stationIndex;

	CustomerScanItem scanItem = new CustomerScanItem(this);
	CustomerWaitingToBag waitToBag = new CustomerWaitingToBag(this);
	ProceedToPayment proceedToPay = new ProceedToPayment(this);
	PayWithCard payWithCard = new PayWithCard(this);
	PayWithCash payWithCash = new PayWithCash(this);
	BlockedScreen blockedScreen = new BlockedScreen(this);
	CustomerStationShutDown shutDown = new CustomerStationShutDown(this);
	StartScreen startScreen = new StartScreen(this);
	CustomerMainScreen mainScreen = new CustomerMainScreen(this);
	EnterPLUCode enterPLU = new EnterPLUCode(this);
	VisualCatalogue lookup = new VisualCatalogue(this);
	CustomerAddBags addBags = new CustomerAddBags(this);
	MemberInfo enterMember;
//	CustomerProductLookup lookup = new CustomerProductLookup();


	public CustomerFrame(int stationIndex) {
		super("Self-Checkout Station: " + Integer.toString(stationIndex + 1));
		this.stationIndex = stationIndex;
		enterMember = new MemberInfo(this);
		addPanels();
		
		// First panel
		cardLayout.show(getContentPane(), "startScreen");

		this.setSize(1280, 720);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void addPanels() {
		
		this.getContentPane().setLayout(cardLayout);

		getContentPane().add(proceedToPay, "proceedToPay");
		getContentPane().add(payWithCash, "payWithCash");
		getContentPane().add(scanItem, "scanItem");
		getContentPane().add(payWithCard, "payWithCard");
		getContentPane().add(waitToBag, "waitToBag");
		getContentPane().add(blockedScreen, "blockedScreen");
		getContentPane().add(shutDown, "shutDown");
		getContentPane().add(startScreen, "startScreen");
		getContentPane().add(mainScreen, "mainScreen");
		getContentPane().add(enterPLU, "enterPLU");
		getContentPane().add(lookup, "lookup");
		getContentPane().add(addBags, "addBags");
		getContentPane().add(enterMember, "enterMember");
	}
	
	public void waitingToBag() {
		cardLayout.show(getContentPane(), "waitToBag");
		waitToBag.waiting();
	}
}
