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

	private JLabel paidLabel = new JLabel("Paid: $0.00");
	private JLabel totalLabel = new JLabel("Total: $0.00");
	
	private double paid = 0.0;
	private double total = 0.0;

	
	public CustomerMainScreen(CustomerFrame cf) {
		super();
		this.customerFrame = cf;
		
		this.setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.75;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		JLabel mainLabel = new JLabel("Cart contains:");
		
		this.add(mainLabel, gbc);
		
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
		
	}
}