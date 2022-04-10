package org.gB.selfcheckout.software.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import org.gB.selfcheckout.software.backend.ItemDatabase;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

/* 
 * Backend integration required:
 *  Hardware:
 *  	none
 *  Software:
 *  	List of PLU products in database
 */

public class VisualCatalogue extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private static String[] itemOptions;
	
	public CustomerFrame customerFrame;
	private JButton backButton;
	private GridBagConstraints gbc = new GridBagConstraints();
	private GridBagConstraints gbc2 = new GridBagConstraints();
	private JPanel bottomPanel;
	private ItemDatabase idb = new ItemDatabase();
	private JLabel firstL, secondL, thirdL;
	private JButton firstB, secondB, thirdB, leftArrow, rightArrow;
	int startingOffset = 0;
	
	public ArrayList<String> itemMenu = new ArrayList<String>();;
	
	public VisualCatalogue(CustomerFrame customerFrame) {
		
		this.customerFrame = customerFrame;
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		setUpBackButton();
		
		setUpItemOptions();
		
		gbc2.insets = new Insets(10, 10, 10, 10);
		
		// Set up prompt text
		bottomPanel.setLayout(new GridBagLayout());

		// Set up directional arrows
		leftArrow = new JButton("<");
		leftArrow.addActionListener(this);
		rightArrow = new JButton(">");
		rightArrow.addActionListener(this);
		gbc2.gridx = 0;
		gbc2.gridy = 1;
		gbc2.ipady = 50;
		gbc2.gridheight = 2;
		bottomPanel.add(leftArrow, gbc2);
		
		
		// Set up "thumbnail" buttons
		firstB = new JButton();
		firstB.addActionListener(this);
		secondB = new JButton();
		secondB.addActionListener(this);
		thirdB = new JButton();
		thirdB.addActionListener(this);
		
		gbc2.gridx = 1;
		gbc2.gridy = 1;
		gbc2.gridheight = 1;
		bottomPanel.add(firstB, gbc2);
		
		gbc2.gridx = 2;
		gbc2.gridy = 1;
		bottomPanel.add(secondB, gbc2);
		
		gbc2.gridx = 3;
		gbc2.gridy = 1;
		bottomPanel.add(thirdB, gbc2);
		

		// Set up product labels
		
		// Set up "thumbnail" buttons
		firstL = new JLabel(itemMenu.get(0));
		secondL = new JLabel(itemMenu.get(1));
		thirdL = new JLabel(itemMenu.get(2));

		gbc2.gridx = 1;
		gbc2.gridy = 2;
		bottomPanel.add(firstL, gbc2);
		
		gbc2.gridx = 2;
		gbc2.gridy = 2;
		bottomPanel.add(secondL, gbc2);
		
		gbc2.gridx = 3;
		gbc2.gridy = 2;
		bottomPanel.add(thirdL, gbc2);
		
		// Right arrow button
		gbc2.gridx = 4;
		gbc2.gridy = 1;
		gbc2.gridheight = 2;
		gbc2.ipady = 50;
		bottomPanel.add(rightArrow, gbc2);
		
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
	
	// This is hardcoded for now, will be integrated with backend later
	private void setUpItemOptions() {
		
		PriceLookupCode plu1 = new PriceLookupCode("7654");
		PLUCodedProduct plup1 = new PLUCodedProduct(plu1, "Red Pepper", new BigDecimal(5.35));
		
		idb.addPLUCodedEntry(plu1, plup1);
		
		PriceLookupCode plu2 = new PriceLookupCode("8889");
		PLUCodedProduct plup2 = new PLUCodedProduct(plu2, "Carrot", new BigDecimal(2.25));
		
		idb.addPLUCodedEntry(plu2, plup2);
		
		PriceLookupCode plu3 = new PriceLookupCode("9087");
		PLUCodedProduct plup3 = new PLUCodedProduct(plu3, "Orange", new BigDecimal(7.99));
		
		idb.addPLUCodedEntry(plu3, plup3);
		
		PriceLookupCode plu4 = new PriceLookupCode("4567");
		PLUCodedProduct plup4 = new PLUCodedProduct(plu4, "Pistachio", new BigDecimal(12.10));
		
		idb.addPLUCodedEntry(plu4, plup4);
		
		PriceLookupCode plu5 = new PriceLookupCode("1243");
		PLUCodedProduct plup5 = new PLUCodedProduct(plu5, "Geranium", new BigDecimal(8.50));
		
		idb.addPLUCodedEntry(plu5, plup5);
		
		PriceLookupCode plu6 = new PriceLookupCode("2837");
		PLUCodedProduct plup6 = new PLUCodedProduct(plu6, "Seed", new BigDecimal(3.79));
		
		idb.addPLUCodedEntry(plu6, plup6);
		
		PriceLookupCode plu7 = new PriceLookupCode("8886");
		PLUCodedProduct plup7 = new PLUCodedProduct(plu7, "Avocado", new BigDecimal(9.98));
		
		idb.addPLUCodedEntry(plu7, plup7);
		
		// Add barcoded products to drop down menu
		ProductDatabases.PLU_PRODUCT_DATABASE.forEach((plu, pluCodedProduct) -> itemMenu.add(pluCodedProduct.getDescription()));
	}
	
	private void updateCatalogue() {
		firstL.setText(itemMenu.get(startingOffset));
		if (itemMenu.size() > startingOffset + 1) {
			secondL.setText(itemMenu.get(startingOffset + 1));
		} else {
			secondL.setText("");
		}
		if (itemMenu.size() > startingOffset + 2) {
			thirdL.setText(itemMenu.get(startingOffset + 2));
		} else {
			thirdL.setText("");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == backButton) {
			// Go back to main customer menu
			this.customerFrame.cardLayout.show(this.customerFrame.getContentPane(), "mainScreen");
		}
		else if (e.getSource() == leftArrow) {
			// Rotate 
			if (startingOffset >= 3) {
				startingOffset -= 3;
			}
			updateCatalogue();
		}
		else if (e.getSource() == rightArrow) {
			if (itemMenu.size() - startingOffset > 3) {
				startingOffset += 3;
			}
			updateCatalogue();
		}
		
	}
	
}
