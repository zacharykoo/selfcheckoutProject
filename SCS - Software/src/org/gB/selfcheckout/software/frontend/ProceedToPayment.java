package org.gB.selfcheckout.software.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ProceedToPayment extends JPanel implements ActionListener {
	
	public CustomerUI customerFrame;

	private static final long serialVersionUID = 1L;
	private JLabel paymentMethod;
	private JButton credit;
	private JButton debit;
	private JButton giftCard;
	private JButton cash;
	private JPanel topPanel;
	private JPanel bottomPanel;
	
	private JButton backButton;

	
	public ProceedToPayment(CustomerUI customerFrame) {
		
		this.customerFrame = customerFrame;

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
		
		credit.addActionListener(this);
		debit.addActionListener(this);
		giftCard.addActionListener(this);
		cash.addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == credit || e.getSource() == debit) {
			
			// Set up a PayWithCardPanel and show it in the CardLayout panel
			PayWithCard pwcPanel = new PayWithCard(this.customerFrame);
			this.customerFrame.mainPanel.add(pwcPanel, "pwcPanel");
			this.customerFrame.cardLayout.show(this.customerFrame.mainPanel, "pwcPanel");
		}
		
		
	}
	
	
}
