package org.gB.selfcheckout.software.frontend;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.*;

public class CustomerUI extends JFrame {
	private static final long serialVersionUID = 1L;
	public CardLayout cardLayout;
	public JPanel mainPanel;
	
	public CustomerUI(String frameName) {
		super(frameName);
		this.setUp();
	}
	
	public void setUp() {
		
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		this.add(mainPanel);
		
		// I'm running proceed to payment first
		ProceedToPayment ptpPanel = new ProceedToPayment(this);
		mainPanel.add(ptpPanel, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}