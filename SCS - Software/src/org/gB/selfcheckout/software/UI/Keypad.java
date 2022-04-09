
package org.gB.selfcheckout.software.UI;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Keypad extends JPanel implements ActionListener{
		
	public CustomerUI customerFrame;	
	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel bottomPanel;		
	
	private static final long serialVersionUID = 1L;
	private JTextField txtFeild;
	private JButton B1;
	private JButton B2;
	private JButton B3;
	private JButton B4;
	private JButton B5;
	private JButton B6;
	private JButton B7;
	private JButton B8;
	private JButton B9;
	private JButton B0;
	private JButton BBackspace;
	private JButton BEnter;
	private JPanel buttonPanel;

	
    
	public Keypad(CustomerUI customerFrame) {
		
		this.customerFrame = customerFrame;
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
		this.bottomPanel.setLayout(new GridLayout(2,1));
		
		
		txtFeild = new JTextField();
		txtFeild.setText("");
		txtFeild.setHorizontalAlignment(SwingConstants.CENTER);
		add(txtFeild);
		txtFeild.setColumns(22);
		txtFeild.setPreferredSize(new Dimension(300, 30));
		
		this.bottomPanel.add(txtFeild);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		this.bottomPanel.add(buttonPanel);
		
		B1 = new JButton("1");
		B2 = new JButton("2");
		B3 = new JButton("3");
		B4 = new JButton("4");
		B5 = new JButton("5");
		B6 = new JButton("6");
		B7 = new JButton("7");
		B8 = new JButton("8");
		B9 = new JButton("9");
		B0 = new JButton("0");
		BBackspace = new JButton("Backspace");
		BEnter = new JButton("Enter");


		buttonPanel.add(B1);
		buttonPanel.add(B2);
		buttonPanel.add(B3);
		buttonPanel.add(B4);
		buttonPanel.add(B5);
		buttonPanel.add(B6);
		buttonPanel.add(B7);
		buttonPanel.add(B8);
		buttonPanel.add(B9);
		buttonPanel.add(B0);
		buttonPanel.add(BBackspace);
		buttonPanel.add(BEnter);

		B1.addActionListener(this);
		B2.addActionListener(this);
		B3.addActionListener(this);
		B4.addActionListener(this);
		B5.addActionListener(this);
		B6.addActionListener(this);
		B7.addActionListener(this);
		B8.addActionListener(this);
		B9.addActionListener(this);
		B0.addActionListener(this);
		BBackspace.addActionListener(this);
		BEnter.addActionListener(this);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
