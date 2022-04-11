
package org.gB.selfcheckout.software.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EnterPLUCode extends JPanel implements ActionListener {

	public CustomerFrame customerFrame;
	private JButton backButton;
	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel bottomPanel;
	private NumericKeypad keypad = new NumericKeypad("Enter PLU Code");
	private JButton enterButton = new JButton("Enter");
	
	public EnterPLUCode(CustomerFrame customerFrame) {

		this.customerFrame = customerFrame;
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		setUpBackButton();
		
//		this.bottomPanel.setLayout(new GridLayout(2,1));
		
		bottomPanel.add(keypad);
		bottomPanel.add(enterButton);
		enterButton.addActionListener(this);
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
			keypad.enteredInfo = "";
			keypad.txtField.setText("Enter PLU Code");
			customerFrame.cardLayout.show(customerFrame.getContentPane(), "mainScreen");
		} else if (e.getSource() == enterButton) {
			// TODO: add item to cart
			
			keypad.enteredInfo = "";
			keypad.txtField.setText("Enter PLU Code");
			customerFrame.cardLayout.show(customerFrame.getContentPane(), "mainScreen");
		}
	}
	
}
