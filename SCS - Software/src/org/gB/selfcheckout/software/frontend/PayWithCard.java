package org.gB.selfcheckout.software.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PayWithCard extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel enterCard;
	
	
	private JButton backButton;
	public CustomerUI customerFrame;
	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel bottomPanel;

	
	public PayWithCard(CustomerUI customerFrame) {
		
		this.customerFrame = customerFrame;

		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
		this.setLayout(new GridLayout(2,1));
		
		setUpBackButton();
		
		this.bottomPanel.setLayout(new GridLayout(2, 1));
		
		enterCard = new JLabel("Please swipe/tap/insert your card", SwingConstants.CENTER);
		enterCard.setFont(new Font("serif", Font.PLAIN, 20));
		
		this.bottomPanel.add(enterCard);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		JButton swipe, tap, insert;
		swipe = new JButton("(SWIPE)");
		tap = new JButton("(TAP)");
		insert = new JButton("(INSERT)");
		buttonPanel.add(swipe);
		buttonPanel.add(tap);
		buttonPanel.add(insert);
		
		this.bottomPanel.add(buttonPanel);

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
		if (e.getSource() == backButton) {
			// Go back to ProceedToPayment Panel
			this.customerFrame.cardLayout.show(this.customerFrame.mainPanel, "ptpPanel");
		}
		
	}
	
	
}
