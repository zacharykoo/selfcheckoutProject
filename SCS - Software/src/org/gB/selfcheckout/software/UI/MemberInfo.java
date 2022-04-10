package org.gB.selfcheckout.software.UI;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*; 
    public class MemberInfo extends JPanel implements ActionListener{
    	public void memberinfo() { 
		super();
            JTextField t1;  
            t1=new JTextField("Enter your member info");  
            t1.setBounds(90,100, 250,30);
            JLabel l1;
    	    l1=new JLabel("Member info :");
    	    l1.setBounds(10,105, 190,20);
    	    this.add(l1);
            this.add(t1);  
    	}
    	@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		} 
         
    }
