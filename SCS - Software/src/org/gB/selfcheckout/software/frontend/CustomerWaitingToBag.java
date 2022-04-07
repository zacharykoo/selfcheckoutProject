package org.gB.selfcheckout.software.frontend;

import javax.swing.*;

public class CustomerWaitingToBag extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel msg;
	private JButton doNotBag;
	
	public CustomerWaitingToBag() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		msg = new JLabel("Please place the scanned item in the bagging area.");
		doNotBag = new JButton("Skip Bagging This Item");
		this.add(msg);
		this.add(doNotBag);
	}
}
