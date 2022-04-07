package org.gB.selfcheckout.software.frontend;

import java.awt.BorderLayout;

import javax.swing.*;

public class CustomerUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public CustomerUI(String frameName) {
		super(frameName);
		this.setUpProceedToPayment();
		
	}
	
	public void setUpProceedToPayment() {
		ProceedToPayment ptpPanel = new ProceedToPayment();
		this.add(ptpPanel, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}