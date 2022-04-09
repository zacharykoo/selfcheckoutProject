package org.gB.selfcheckout.software.UI;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/*
 * Screen shown when the station is powered on, but no customer has started yet
 */

public class StartScreen extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private CustomerFrame customerFrame;
	
	public StartScreen(CustomerFrame cf) {
		super();
		this.customerFrame = cf;
		
		JButton startButton = new JButton("START");
		startButton.setFont(new Font("serif", Font.PLAIN, 20));
		startButton.addActionListener(this);
		this.setLayout(new GridLayout(1,1));
		this.add(startButton);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
