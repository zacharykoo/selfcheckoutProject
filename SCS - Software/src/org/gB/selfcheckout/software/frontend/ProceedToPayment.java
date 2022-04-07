package org.gB.selfcheckout.software.frontend;

import javax.swing.*;

public class ProceedToPayment extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel paymentMethod;
	private JButton credit;
	private JButton debit;
	private JButton giftCard;
	private JButton cash;

	
	public ProceedToPayment() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		paymentMethod = new JLabel("Select a payment method");
		credit = new JButton("Credit");
		debit = new JButton("Debit");
		giftCard = new JButton("Gift Card");
		cash = new JButton("Cash");
		this.add(paymentMethod);
		this.add(credit);
		this.add(debit);
		this.add(giftCard);
		this.add(cash);

	}
	
	
}
