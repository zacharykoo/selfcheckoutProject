package org.gB.selfcheckout.software.UI;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/*
 * Main screen for user functionality
 */

public class CustomerMainScreen extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private CustomerFrame customerFrame;
	private GridBagConstraints gbc = new GridBagConstraints();
	
	private JButton scan = new JButton("Scan Item");
	private JButton enterCode = new JButton("Enter PLU Code");
	private JButton lookup = new JButton("Look Up Item");
	private JButton remove = new JButton("Remove Item");
	private JButton pay = new JButton("Pay");
	private JButton addBags = new JButton("Add Bags");

	private JLabel paidLabel = new JLabel();
	private JLabel totalLabel = new JLabel();
	
	private double paid = 0.0;
	private double total = 0.0;

	
	public CustomerMainScreen(CustomerFrame cf) {
		super();
		this.customerFrame = cf;
		
		this.setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		
		// Show cart contents
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.75;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		JLabel mainLabel = new JLabel("Cart contains:");
		
		this.add(mainLabel, gbc);
		
		
		// Show subtotals
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 0.0;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		JPanel subtotalsPanel = new JPanel();
		
		this.add(subtotalsPanel, gbc);
		subtotalsPanel.setLayout(new BoxLayout(subtotalsPanel, BoxLayout.Y_AXIS));
		
		paidLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		paidLabel.setText(String.format("Paid: $%.2f", paid));
		totalLabel.setText(String.format("Total: $%.2f", total));
		
		subtotalsPanel.add(paidLabel);
		subtotalsPanel.add(totalLabel);

		
		// Buttons for functionality
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weighty = 1.0;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		JPanel buttonsPanel = new JPanel();
		
		this.add(buttonsPanel, gbc);
		
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		scan.addActionListener(this);
		scan.setAlignmentX(Component.CENTER_ALIGNMENT);
		enterCode.addActionListener(this);
		enterCode.setAlignmentX(Component.CENTER_ALIGNMENT);
		lookup.addActionListener(this);
		lookup.setAlignmentX(Component.CENTER_ALIGNMENT);
		remove.addActionListener(this);
		remove.setAlignmentX(Component.CENTER_ALIGNMENT);
		addBags.addActionListener(this);
		addBags.setAlignmentX(Component.CENTER_ALIGNMENT);
		pay.addActionListener(this);
		pay.setAlignmentX(Component.CENTER_ALIGNMENT);

		buttonsPanel.add(scan);
		buttonsPanel.add(lookup);
		buttonsPanel.add(enterCode);
		buttonsPanel.add(remove);
		buttonsPanel.add(addBags);
		buttonsPanel.add(pay);

		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == scan) {
			customerFrame.cardLayout.show(customerFrame.getContentPane(), "scanItem");
		}
		else if (e.getSource() == enterCode) {
			
		}
		else if (e.getSource() == lookup) {
	
		}
		else if (e.getSource() == remove) {
	
		}
		else if (e.getSource() == addBags) {
			
		}
		else if (e.getSource() == pay) {
			customerFrame.cardLayout.show(customerFrame.getContentPane(), "proceedToPay");
		}


	}
}