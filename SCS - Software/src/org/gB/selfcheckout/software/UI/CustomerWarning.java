package org.gB.selfcheckout.software.UI;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * TODO: Complete this comment.
 */
public class CustomerWarning extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel msg;
	
	/**
	 * TODO: Complete this comment.
	 */
	public CustomerWarning() {
		super();
		JLabel msg = new JLabel();
		this.setLayout(new GridBagLayout());
		this.add(msg);
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
