package org.gB.selfcheckout.software.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * JPanel for the customer to enter a payment amount.
 */
public class EnterAmountToPay extends JPanel {
	private static final long serialVersionUID = 1L;
	private CustomerFrame customerFrame;
	private NumericKeypad keypad = new NumericKeypad("Enter Payment Amount");
	private JButton entireAmount = new JButton("Entire Amount");
	private JButton backButton;
	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel bottomPanel;
	private JButton enterButton = new JButton("Enter");

	/**
	 * Initializes the interface.
	 * 
	 * @param customerFrame
	 * 		The instance of CustomerFrame that owns this panel.
	 */
	public EnterAmountToPay(CustomerFrame customerFrame) {
		super();
		this.customerFrame = customerFrame;
		this.setLayout(new GridBagLayout());
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.5;
		gbc.weightx = 0.5;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		this.add(keypad, gbc);
		
		gbc.weightx = 0.0;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weighty = 0.3;
		this.add(entireAmount, gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 0.0;
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
		this.add(enterButton, gbc);
		
		// Store the entered member number and return to the main screen.
		enterButton.addActionListener(e ->{
			// TODO: Update the state with the paid amount.
			keypad.enteredInfo = "";
			keypad.txtField.setText("");
			customerFrame.cardLayout.show(customerFrame.getContentPane(), "mainScreen");
		});
		
		entireAmount.addActionListener(e ->{
			// TODO: Update the state with the paid amount.
			keypad.enteredInfo = "";
			keypad.txtField.setText("");
			customerFrame.cardLayout.show(customerFrame.getContentPane(), "mainScreen");
		});
	}
	
}
