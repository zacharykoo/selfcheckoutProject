package org.gB.selfcheckout.software.UI;

import java.awt.Font;
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
	
	public CustomerMainScreen(CustomerFrame cf) {
		super();
		this.customerFrame = cf;
		
		JLabel msg = new JLabel("Main Screen", SwingConstants.CENTER);
		msg.setFont(new Font("serif", Font.PLAIN, 20));
		this.setLayout(new GridLayout(1,1));
		this.add(msg);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}