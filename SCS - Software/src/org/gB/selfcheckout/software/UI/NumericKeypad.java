
package org.gB.selfcheckout.software.UI;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NumericKeypad extends JPanel {
		
	private static final long serialVersionUID = 1L;

	private JTextField txtFeild;
    
	public NumericKeypad(CustomerUI customerFrame) {
		

		txtFeild = new JTextField();
		txtFeild.setText("");
		txtFeild.setHorizontalAlignment(SwingConstants.CENTER);
		add(txtFeild);
		txtFeild.setColumns(22);
		txtFeild.setPreferredSize(new Dimension(300, 30));
		
		JButton B1 = new JButton("1");
		B1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFeild.setText("1");
			}
		});
		add(B1);
		
		JButton B2 = new JButton("2");
		B2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFeild.setText("2");
			}
		});
		add(B2);
		
		JButton B3 = new JButton("3");
		B3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFeild.setText("3");
			}
		});
		add(B3);
		
		JButton B4 = new JButton("4");
		B4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFeild.setText("4");

			}
		});
		add(B4);
		
		JButton B5 = new JButton("5");
		B5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFeild.setText("5");
			}
		});
		add(B5);
		
		JButton B6 = new JButton("6");
		B6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFeild.setText("6");
			}
		});
		add(B6);
		
		JButton B7 = new JButton("7");
		B7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFeild.setText("7");
			}
		});
		add(B7);
		
		JButton B8 = new JButton("8");
		B8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFeild.setText("8");
			}
		});
		add(B8);
		
		JButton B9 = new JButton("9");
		B9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFeild.setText("9");
			}
		});
		add(B9);
		
		JButton B0 = new JButton("0");
		B0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFeild.setText("0");
			}
		});
		add(B0);
		
		JButton BBackspace = new JButton("Backspace");
		add(BBackspace);
		
		JButton BEnter = new JButton("Enter");
		add(BEnter);

	}

}
