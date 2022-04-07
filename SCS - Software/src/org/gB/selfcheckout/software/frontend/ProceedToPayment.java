package org.gB.selfcheckout.software.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ProceedToPayment extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel paymentMethod;
	private JButton credit;
	private JButton debit;
	private JButton giftCard;
	private JButton cash;
	private JPanel topPanel;
	private JPanel bottomPanel;

	
	public ProceedToPayment() {

		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 40, 40));
		this.setLayout(new GridLayout(2, 1));
		
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2,2));
		this.add(topPanel);
		this.add(bottomPanel);
		
		
		paymentMethod = new JLabel("Select a payment method");
		paymentMethod.setFont(new Font("serif", Font.PLAIN, 20));
		credit = new JButton("Credit");
		debit = new JButton("Debit");
		giftCard = new JButton("Gift Card");
		cash = new JButton("Cash");
		
		
		topPanel.add(paymentMethod);
		bottomPanel.add(credit);
		bottomPanel.add(debit);
		bottomPanel.add(giftCard);
		bottomPanel.add(cash);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
