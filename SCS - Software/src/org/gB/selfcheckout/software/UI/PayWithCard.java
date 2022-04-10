package org.gB.selfcheckout.software.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PayWithCard extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel enterCard;
	
	private NumericKeypad keypad = new NumericKeypad("Enter PIN");
	private JPanel panel;

	private JButton backButton;
	public CustomerFrame customerFrame;
	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel bottomPanel;
	private JButton swipe, tap, insert;

	
	public PayWithCard(CustomerFrame customerFrame) {
		
		this.customerFrame = customerFrame;

		//setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
		setLayout(new GridLayout(2,1));
		
		setUpBackButton();
		
		bottomPanel.setLayout(new GridLayout(3, 1));
		
		enterCard = new JLabel("Please swipe/tap/insert your card", SwingConstants.CENTER);
		enterCard.setFont(new Font("serif", Font.PLAIN, 20));
		
		bottomPanel.add(enterCard);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		swipe = new JButton("(SWIPE)");
		tap = new JButton("(TAP)");
		insert = new JButton("(INSERT)");
		swipe.addActionListener(this);
		tap.addActionListener(this);
		insert.addActionListener(this);
		buttonPanel.add(swipe);
		buttonPanel.add(tap);
		buttonPanel.add(insert);
		
		bottomPanel.add(buttonPanel);
		
		keypad.setEnabled(false);
		keypad.setVisible(false);
		bottomPanel.add(keypad);

	}

	public void setUpBackButton() {
		
		gbc.insets = new Insets(3, 3, 3, 3);
		
		setLayout(new GridBagLayout());
		
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
			keypad.setVisible(false);
			customerFrame.cardLayout.show(customerFrame.getContentPane(), "proceedToPay");
		}
		else if (e.getSource() == insert) {
			// ask for pin
			keypad.setEnabled(true);
			keypad.setVisible(true);
		}
		
	}
	
	
}
