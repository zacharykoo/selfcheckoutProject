
package org.gB.selfcheckout.software.UI;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NumericKeypad extends JPanel {
		
	private static final long serialVersionUID = 1L;

	public JTextField txtField;
	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel numPanel = new JPanel();
	private JPanel otherPanel = new JPanel();	
    
	public NumericKeypad(String msg) {
		super();
		
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		setLayout(new GridLayout(3, 1));
		
		gbc.insets = new Insets(3, 3, 3, 3);
		
		txtField = new JTextField();
		txtField.setText(msg);
		txtField.setHorizontalAlignment(SwingConstants.CENTER);
		txtField.setColumns(22);
		txtField.setPreferredSize(new Dimension(300, 30));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.5;
		
		add(txtField);

		numPanel.setLayout(new GridLayout(2, 5));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weighty = 0.75;
		add(numPanel);
		
		JButton B1 = new JButton("1");
		B1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtField.setText(txtField.getText() + "1");
			}
		});
		numPanel.add(B1);
		
		JButton B2 = new JButton("2");
		B2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtField.setText(txtField.getText() +"2");
			}
		});
		numPanel.add(B2);
		
		JButton B3 = new JButton("3");
		B3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtField.setText(txtField.getText() +"3");
			}
		});
		numPanel.add(B3);
		
		JButton B4 = new JButton("4");
		B4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtField.setText(txtField.getText() +"4");

			}
		});
		numPanel.add(B4);
		
		JButton B5 = new JButton("5");
		B5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtField.setText(txtField.getText() +"5");
			}
		});
		numPanel.add(B5);
				
		JButton B6 = new JButton("6");
		B6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtField.setText(txtField.getText() +"6");
			}
		});
		numPanel.add(B6);
		
		JButton B7 = new JButton("7");
		B7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtField.setText(txtField.getText() +"7");
			}
		});
		numPanel.add(B7);
		
		JButton B8 = new JButton("8");
		B8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtField.setText(txtField.getText() +"8");
			}
		});
		numPanel.add(B8);
		
		JButton B9 = new JButton("9");
		B9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtField.setText(txtField.getText() +"9");
			}
		});
		numPanel.add(B9);
		
		JButton B0 = new JButton("0");
		B0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtField.setText(txtField.getText() +"0");
			}
		});
		numPanel.add(B0);
		
		otherPanel.setLayout(new GridLayout(1, 2));
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weighty = 0.3;
		
		
		JButton BBackspace = new JButton("Erase");
		add(BBackspace);
		BBackspace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtField.setText("");
			}
		});
		
		JButton BEnter = new JButton("Enter");
		BEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Do stuff
			}
		});
		add(BEnter);
		
		otherPanel.add(BBackspace);
		otherPanel.add(BEnter);
		
		add(otherPanel);

		
	}

}
