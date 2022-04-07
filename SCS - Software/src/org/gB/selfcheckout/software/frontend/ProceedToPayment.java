package org.gB.selfcheckout.software.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ProceedToPayment extends JPanel implements ActionListener {
	
	public CustomerUI customerFrame;
	private JButton backButton;
	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel bottomPanel;	

	private static final long serialVersionUID = 1L;
	private JLabel paymentMethod;
	private JButton credit;
	private JButton debit;
	private JButton giftCard;
	private JButton cash;
	
	private JPanel bottom1;
	private JPanel bottom2;
	

	
	public ProceedToPayment(CustomerUI customerFrame) {
				
		this.customerFrame = customerFrame;
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
		
		setUpBackButton();

		this.bottomPanel.setLayout(new GridLayout(2,1));
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
		
		gbc.insets = new Insets(3, 3, 3, 3);
		
		this.setLayout(new GridBagLayout());
		
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.0;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		
		backButton.addActionListener(this);
		this.add(backButton, gbc);
		
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		bottomPanel = new JPanel();
		this.add(bottomPanel, gbc);
		
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
