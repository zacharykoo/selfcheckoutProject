package org.gB.selfcheckout.software.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * JPanel that implements the main menu interface of the attendant UI.
 */
public class AttendantMainMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	private AttendantFrame attendantFrame;
	private BorderLayout border = new BorderLayout(); // Outermost layout.
	// Top contents that contains controls for each self-checkout station:
	private JTabbedPane tabs = new JTabbedPane();
	// Bottom layout for navigation controls:
	private JPanel bottomPanel = new JPanel();
	private JButton logoutButton = new JButton("Logout");
	private JButton lookupButton = new JButton("Lookup Product");
	
	/**
	 * Initializes the main menu interface with the specified number of
	 * self-checkout stations.
	 * 
	 * @param stations
	 * 		The number of self-checkout stations that this attendant manages.
	 */
	
	public AttendantMainMenu(int stations, AttendantFrame attendantFrame) {
		super();
		this.setLayout(border); // Set the outermost layout.
		this.attendantFrame = attendantFrame;
		// Instantiate the navigation buttons, add them to the bottom panel:
		
		this.add(bottomPanel);
		
		bottomPanel.add(logoutButton);
		bottomPanel.add(lookupButton);
		border.addLayoutComponent(bottomPanel, BorderLayout.SOUTH);
		// Create the tabs to manage each self-checkout station:
		for (int i = 0; i < stations; i ++) tabs.add(
				"Station " + Integer.toString(i + 1),
				new StationInterface(i));
		border.addLayoutComponent(tabs, BorderLayout.SOUTH);
		
		// Setup event handlers:
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				Container parent = AttendantMainMenu.this.getParent();
//				parent.removeAll();
//				parent.add(((AttendantFrame) parent).login);
				attendantFrame.cardLayout.show(attendantFrame.getContentPane(), "login");
			}});
		
		lookupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				attendantFrame.cardLayout.show(attendantFrame.getContentPane(), "lookup");
			}});
		this.setVisible(true);
	}
	
	/**
	 * JPanel that implements the attendant controls of a single self-checkout
	 * system.
	 */
	private class StationInterface extends JPanel {
		private static final long serialVersionUID = 1L;
		private BorderLayout border = new BorderLayout(); // Outermost layout.
		// Top row of the interface:
		private JPanel top = new JPanel();
		private JButton power = new JButton("Toggle Station Power");
		private JButton blockStation = new JButton("Block Station");
		private JButton viewCart = new JButton("View Scanned Items");
		// Middle row of the interface:
		private JPanel middle = new JPanel();
		private JButton refillPaper = new JButton("refill Printer Paper");
		private JButton refillInk = new JButton("refill Printer Ink");
		// Bottom row of the interface:
		private JPanel bottom = new JPanel();
		private JMenu refillCoins = new JMenu("refill Coins");
		private JMenu refillBanknotes = new JMenu("refill Banknotes");
		private JButton emptyCoins = new JButton("Empty Coin Storage");
		private JButton emptyBanknotes = new JButton("Empty Banknote Storage");
		// The index of the associated self-checkout station.
		private int stationIndex;

		/**
		 * Initialize the self-checkout station attendant controls, relating
		 * the controls to the station with the specified index.
		 * 
		 * @param index
		 * 		The "number" of the self-checkout station whose attendant
		 * 		controls will be manipulated with this instance.
		 */
		public StationInterface(int index) {
			// Set the station number and main layout.
			stationIndex = index;
			// Setup the top row UI:
			top.add(power);
			top.add(blockStation);
			top.add(viewCart);
			border.addLayoutComponent(top, BorderLayout.NORTH);
			// Setup the middle row UI:
			middle.add(refillPaper);
			middle.add(refillInk);
			border.addLayoutComponent(middle, BorderLayout.CENTER);
			// Setup the bottom row UI:
			refillCoins.add("$0.05");
			refillCoins.add("$0.10");
			refillCoins.add("$0.25");
			refillCoins.add("$1.00");
			refillCoins.add("$2.00");
			refillBanknotes.add("$5.00");
			refillBanknotes.add("$10.00");
			refillBanknotes.add("$20.00");
			refillBanknotes.add("$50.00");
			middle.add(emptyCoins);
			middle.add(emptyBanknotes);
			border.addLayoutComponent(bottom, BorderLayout.SOUTH);
			
			// Set up event handlers:
			power.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				}});
			
			blockStation.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				}});
			
			viewCart.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				}});
			
			refillPaper.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				}});
			
			refillInk.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				}});
			
			refillCoins.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				}});
			
			refillBanknotes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				}});
			
			emptyCoins.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				}});
			
			emptyBanknotes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				}});
		}
	}
}
