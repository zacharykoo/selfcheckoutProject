package org.gB.selfcheckout.software.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
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
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(new JLabel("Please enter your member number:"));
		this.add(new NumericKeypad(""));
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
