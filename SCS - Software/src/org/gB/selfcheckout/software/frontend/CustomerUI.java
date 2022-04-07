package org.gB.selfcheckout.software.frontend;

import javax.swing.*;

public class CustomerUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public CustomerUI(String frameName) {
		super(frameName);
		this.setUpProceedToPayment();
		
	}
	
	public void setUpProceedToPayment() {
		ProceedToPayment ptpPanel = new ProceedToPayment();
		this.add(ptpPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}