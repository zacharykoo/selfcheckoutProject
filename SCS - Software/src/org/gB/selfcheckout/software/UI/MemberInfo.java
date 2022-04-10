package org.gB.selfcheckout.software.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * TODO: Complete this comment.
 */
public class MemberInfo extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	/**
	 * TODO: Complete this comment.
	 */
	public MemberInfo() {
		super();
		JTextField t1;
		t1 = new JTextField("Please enter your member info.");
		t1.setBounds(90,100, 250,30);
		JLabel l1;
		l1 = new JLabel("Member info :");
		l1.setBounds(10,105, 190,20);
		this.add(l1);
		this.add(t1);
	}
	
	/**
	 * TODO: Complete this comment.
	 * 
	 * @param e
	 */
    @Override
	public void actionPerformed(ActionEvent e) {
    		
	} 
}
