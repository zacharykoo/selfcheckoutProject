package org.gB.selfcheckout.software.UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/*
 * Stubbed class for now, until actual blocked screen is added
 */

public class BlockedScreen extends JPanel implements ActionListener {

	private CustomerFrame customerFrame;
	
	public BlockedScreen(CustomerFrame cf) {
		super();
		this.customerFrame = cf;
		
		JLabel msg = new JLabel("Blocked screen", SwingConstants.CENTER);
		this.setLayout(new GridLayout(1,1));
		this.add(msg);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
