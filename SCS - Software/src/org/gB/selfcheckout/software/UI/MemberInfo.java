package org.gB.selfcheckout.software.UI;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * JPanel for the customer to enter their membership number.
 */
public class MemberInfo extends JPanel {
	private static final long serialVersionUID = 1L;
	private CustomerFrame cutomerFrame;
	private NumericKeypad keypad = new NumericKeypad("");
	private JButton enterButton = new JButton("Enter");

	/**
	 * Initializes the interface.
	 * 
	 * @param customerFrame
	 * 		The instance of CustomerFrame that owns this panel.
	 */
	public MemberInfo(CustomerFrame cutomerFrame) {
		super();
		this.cutomerFrame = cutomerFrame;
		this.setLayout(new GridLayout(2, 1));
		this.add(new JLabel("Please enter your member number:"));
		
		this.add(keypad);
		this.add(enterButton);
		
		// Store the entered member number and return to the main screen.
		enterButton.addActionListener(e ->{
			// TODO: Update the state with the member number.
			cutomerFrame.cardLayout.show(cutomerFrame.getContentPane(), "mainScreen");
		});
	}
}
