
package org.gB.selfcheckout.software.UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class LookupProduct extends JPanel {
	private JTextField txtLookupItem;


	public LookupProduct() {
		
		JButton backButton = new JButton("Back");
		backButton.setHorizontalAlignment(SwingConstants.LEFT);
		add(backButton);
		
		JLabel lblEnterPluCode = new JLabel("                  Enter PLU code for item:                              ");
		add(lblEnterPluCode);
		
		txtLookupItem = new JTextField();
		add(txtLookupItem);
		txtLookupItem.setColumns(40);
		
		JButton BLookup = new JButton("Look up");
		add(BLookup);
		
		JButton BShowKeypad = new JButton("Show Keypad");
		add(BShowKeypad);
		
		JButton btnAddToCart = new JButton("Add to cart");
		add(btnAddToCart);

	}

}
