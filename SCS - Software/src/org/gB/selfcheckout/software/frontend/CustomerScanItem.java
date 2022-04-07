package org.gB.selfcheckout.software.frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CustomerScanItem extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	public CustomerUI customerFrame;
	private JButton backButton;
	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel bottomPanel;
	
	public CustomerScanItem(CustomerUI customerFrame) {
		
		this.customerFrame = customerFrame;
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
		
		setUpBackButton();
		
		this.bottomPanel.setLayout(new GridLayout(2,1));
		JLabel scanLabel = new JLabel("Please scan your item");
		bottomPanel.add(scanLabel);
		JButton scanButton = new JButton("(SCAN)");
		bottomPanel.add(scanButton);
		
	}
	
	
	public void setUpBackButton() {
		
		gbc.insets = new Insets(3, 3, 3, 3);
		
		this.setLayout(new GridBagLayout());
		
		// Set up back button at the top left
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.0;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		
		this.add(backButton, gbc);
		
		// Create another panel for the rest of the screen
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
		// TODO Auto-generated method stub
		
	}
	
}
