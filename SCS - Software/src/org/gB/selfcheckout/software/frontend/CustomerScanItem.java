package org.gB.selfcheckout.software.frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CustomerScanItem extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	public CustomerUI customerFrame;
	private JButton backButton;
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public CustomerScanItem(CustomerUI customerFrame) {
		
		this.customerFrame = customerFrame;
		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 40, 40));
		
		setUpBackButton();
		
		
	}
	
	
	public void setUpBackButton() {
		
		this.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		this.add(backButton, gbc);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
