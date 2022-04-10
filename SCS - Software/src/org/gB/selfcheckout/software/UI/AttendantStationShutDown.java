package org.gB.selfcheckout.software.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/*
 * Stubbed class for now, until actual shut down screen is added
 * When attendant starts up a station, this panel will change
 */

public class AttendantStationShutDown extends JPanel {

	private CustomerFrame customerFrame;
	
	public AttendantStationShutDown(CustomerFrame cf) {
		super();
		this.customerFrame = cf;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.setBackground(Color.GRAY);
		JLabel msg = new JLabel("Attendant Station Shut Down", SwingConstants.CENTER);
		msg.setFont(new Font("serif", Font.PLAIN, 20));
		this.setLayout(new GridLayout(1,1));
		this.add(msg);
		
		JButton powerOn = new JButton("Power On");
		add(powerOn);
	}
	
}
