package org.gB.selfcheckout.software.UI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * TODO: Complete this comment.
 */
public class AlertPage extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel msg;
	
	/**
	 * TODO: Complete this comment.
	 */
	public AlertPage() {
		super();
		msg = new JLabel();
		JButton button = new JButton("Accept");
		msg.setBounds(50,50, 100,30);   
		button.setBounds(50,100, 100,30);  
		this.add(msg); this.add(button);      
	} 
	
	/**
	 * TODO: Complete this comment.
	 * 
	 * @param msg
	 */
	public void setText(String msg) {
		this.msg.setText(msg);
	}
}