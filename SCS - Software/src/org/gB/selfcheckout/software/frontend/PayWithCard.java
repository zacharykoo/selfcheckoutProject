package org.gB.selfcheckout.software.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PayWithCard extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel enterCard;
	
	
	private JButton backButton;
	private JPanel backPanel;
	public CustomerUI customerFrame;
	private JPanel topPanel;
	private JPanel bottomPanel;

		
	public PayWithCard(CustomerUI customerFrame) {
		
		this.customerFrame = customerFrame;

		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 40, 40));
		this.setLayout(new GridLayout(2,1));
		
		setUpBackButton();
		
		enterCard = new JLabel("Please swipe/tap/insert your card");
		enterCard.setFont(new Font("serif", Font.PLAIN, 20));
		
		this.bottomPanel.add(enterCard);
	}

	public void setUpBackButton() {
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2, 1));
		this.add(topPanel);
		this.add(bottomPanel);
		
		backButton = new JButton("Back");
		backPanel = new JPanel();
		backButton.addActionListener(this);
		topPanel.add(backButton, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			// Go back to ProceedToPayment Panel
			this.customerFrame.cardLayout.show(this.customerFrame.mainPanel, "ptpPanel");
		}
		
	}
	
	
}
