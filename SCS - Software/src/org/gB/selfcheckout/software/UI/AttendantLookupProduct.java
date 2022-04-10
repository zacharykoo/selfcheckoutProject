package org.gB.selfcheckout.software.UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class AttendantLookupProduct extends JPanel {
	private JTextField txtLookupItem;


	public AttendantLookupProduct() {
		
		JButton backButton = new JButton("Back");
		backButton.setHorizontalAlignment(SwingConstants.LEFT);
		add(backButton);
		
		JLabel lblEnterPluCode = new JLabel("                  Enter the product's name:                         ");
		add(lblEnterPluCode);
		
		txtLookupItem = new JTextField();
		add(txtLookupItem);
		txtLookupItem.setColumns(40);
		
		JButton BLookup = new JButton("Look up");
		add(BLookup);

	}

}
