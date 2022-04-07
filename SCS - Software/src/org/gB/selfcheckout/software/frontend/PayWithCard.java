package org.gB.selfcheckout.software.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PayWithCard extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel enterCard;
	private JButton backButton;
	public CustomerUI customerFrame;


	
	public PayWithCard(CustomerUI customerFrame) {
		
		this.customerFrame = customerFrame;
		
		setUpBackButton();
		
		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 40, 40));
		this.setLayout(new FlowLayout());
	
		
		enterCard = new JLabel("Please swipe/tap/insert your card");
		enterCard.setFont(new Font("serif", Font.PLAIN, 20));
		
		this.add(enterCard);
	}

	public void setUpBackButton() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
