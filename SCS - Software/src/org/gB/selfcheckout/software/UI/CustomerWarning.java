package org.gB.selfcheckout.software.UI;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * JPanel to indicate to the customer that their interface is locked out pending
 * assistance from the attendant.
 */
public class CustomerWarning extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel msg;
	
	/**
	 * Initializes the lockout interface with an empty message.
	 */
	public CustomerWarning() {
		super();
		JLabel msg = new JLabel();
		this.setLayout(new GridBagLayout());
		this.add(msg);
	}
	
	/**
	 * Sets the lockout message shown on this interface.
	 * 
	 * @param msg
	 * 		The message to be shown.
	 */
	public void setText(String msg) {
		this.msg.setText(msg);
	}
}
