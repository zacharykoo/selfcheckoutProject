package org.gB.selfcheckout.software.UI;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*; 
    public class MemberInfo extends JFrame implements ActionListener{
    	public static void main(String[] args) {
    		JFrame frame= new JFrame("Membership Info");  
            JTextField t1;  
            t1=new JTextField("Enter your member info");  
            t1.setBounds(90,100, 250,30);
            JLabel l1;
    	    l1=new JLabel("Member info :");
    	    l1.setBounds(10,105, 190,20);
    	    frame.add(l1);
            frame.add(t1);  
            frame.setSize(400,400);  
            frame.setLayout(null);  
            frame.setVisible(true); 
    	}
    	@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		} 
         
    }