package org.gB.selfcheckout.software.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import org.gB.selfcheckout.software.backend.ItemDatabase;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class CustomerScanItem extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private static String[] itemOptions;
	
	public CustomerFrame customerFrame;
	private JButton backButton;
	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel bottomPanel;
	private ItemDatabase idb = new ItemDatabase();
	private JComboBox itemMenu = new JComboBox();
	private JButton scanButton;
		
	public CustomerScanItem(CustomerFrame customerFrame) {
		
		this.customerFrame = customerFrame;
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
		
		setUpBackButton();
		
		setUpItemOptions();
		
		this.bottomPanel.setLayout(new GridLayout(3,1));
		JLabel scanLabel = new JLabel("Select an item to scan");
		bottomPanel.add(scanLabel);
		
		bottomPanel.add(itemMenu);
		
		scanButton = new JButton("(SCAN)");
		scanButton.addActionListener(this);
		bottomPanel.add(scanButton);
		
	}
	
	
	public void setUpBackButton() {
		
		gbc.insets = new Insets(3, 3, 3, 3);
		
		this.setLayout(new GridBagLayout());
		
		// Set up back button at the top left
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.0;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		
		this.add(backButton, gbc);
		
		// Create another panel for the rest of the screen
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		bottomPanel = new JPanel();
		this.add(bottomPanel, gbc);
		
	}
	
	private void setUpItemOptions() {
		
		Numeral[] num = {Numeral.five, Numeral.four, Numeral.three, Numeral.two, Numeral.one};
		Barcode bc1 = new Barcode(num);
		BarcodedProduct bcp1 = new BarcodedProduct(bc1, "Lucky Charms", new BigDecimal(5.35), 15.5);
		
		idb.addBarcodedEntry(bc1, bcp1);
		
		Numeral[] num2 = {Numeral.seven, Numeral.nine, Numeral.four, Numeral.zero, Numeral.four};
		Barcode bc2 = new Barcode(num2);
		BarcodedProduct bcp2 = new BarcodedProduct(bc2, "Greek Yogurt", new BigDecimal(7.99), 13.75);
		
		idb.addBarcodedEntry(bc2, bcp2);
		
		// Add barcoded products to drop down menu
		ProductDatabases.BARCODED_PRODUCT_DATABASE.forEach((barcode, barcodedProduct) -> 
				itemMenu.addItem(barcodedProduct.getDescription()));
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == backButton) {
			// Go back to main customer menu
		
		}
		else if (e.getSource() == scanButton) {
			// Go to "place your item in bagging area" panel
			this.customerFrame.waitingToBag();
		}
		
	}
	
}
