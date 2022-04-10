package org.gB.selfcheckout.software.UI;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*; 
public class CustomerWarning extends JPanel {
	public void warningPage() {
		super();
	      JLabel label = new JLabel("Warning Please wait for an employee to attend to you");
	      this.setLayout(new GridBagLayout());
	      this.add(label);
	   }
	public setText(String msg) {
		this.label.setText(msg);
	}
}  
