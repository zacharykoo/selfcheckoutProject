package org.gB.selfcheckout.software.UI;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*; 
	public class AlertPage extends JPanel {
		public void alertPage() {
			super();
			    JLabel label;
			    JButton button = new JButton("accept");  
			    label=new JLabel("Alert.");  
			    label.setBounds(50,50, 100,30);   
			    button.setBounds(50,100, 100,30);  
			    this.add(label); this.add(button);  
			    
		} 
		public setText(String msg) {
			this.label.setText(msg);
		}
        
	}
