package org.gB.selfcheckout.software.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ProceedToPayment extends JPanel implements ActionListener {
	
	public CustomerUI customerFrame;
	private JButton backButton;

	private static final long serialVersionUID = 1L;
	private JLabel paymentMethod;
	private JButton credit;
	private JButton debit;
	private JButton giftCard;
	private JButton cash;
	private JPanel topPanel;
	private JPanel bottomPanel;
	
	private JPanel bottom1;
	private JPanel bottom2;
	

	
	public ProceedToPayment(CustomerUI customerFrame) {
				
		this.customerFrame = customerFrame;

		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 40, 40));
		this.setLayout(new GridLayout(2,1));
		
		setUpBackButton();

		bottom1 = new JPanel();
		bottom1.setLayout(new FlowLayout());
		bottom2 = new JPanel();
		bottom2.setLayout(new GridLayout(2,2));
		this.bottomPanel.add(bottom1);
		this.bottomPanel.add(bottom2);
		
		paymentMethod = new JLabel("Select a payment method", SwingConstants.CENTER);
		paymentMethod.setFont(new Font("serif", Font.PLAIN, 20));
		credit = new JButton("Credit");
		debit = new JButton("Debit");
		giftCard = new JButton("Gift Card");
		cash = new JButton("Cash");
		
		
		bottom1.add(paymentMethod);
		bottom2.add(credit);
		bottom2.add(debit);
		bottom2.add(giftCard);
		bottom2.add(cash);
		
		credit.addActionListener(this);
		debit.addActionListener(this);
		giftCard.addActionListener(this);
		cash.addActionListener(this);
		
	}
	
	public void setUpBackButton() {
		// Set up panel for back button (top) and rest (bottom)
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2, 1));
		this.add(topPanel);
		this.add(bottomPanel);
		
		backButton = new JButton("Back");
		backButton.setBounds(50, 50, 75, 45);;
		backButton.addActionListener(this);
		topPanel.add(backButton, BorderLayout.EAST);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == credit || e.getSource() == debit) {
			
			// Set up a PayWithCardPanel and show it in the CardLayout panel
			PayWithCard pwcPanel = new PayWithCard(this.customerFrame);
			this.customerFrame.mainPanel.add(pwcPanel, "pwcPanel");
			this.customerFrame.cardLayout.show(this.customerFrame.mainPanel, "pwcPanel");
		} 
		else if (e.getSource() == backButton) {
			//this.customerFrame.cardLayout.show(this.customerFrame.mainPanel, "XXX");
		}
		
		
	}
	
	
}
