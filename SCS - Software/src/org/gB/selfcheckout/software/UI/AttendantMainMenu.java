package org.gB.selfcheckout.software.UI;

import java.awt.BorderLayout;
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
	private BorderLayout border; // Outermost layout.
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
	public AttendantMainMenu(int stations) {
		this.setLayout(border); // Set the outermost layout.
		// Instantiate the navigation buttons, add them to the bottom panel:
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
				// TODO Auto-generated method stub
			}});
		
		lookupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}});
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
		private JButton refilPaper = new JButton("Refil Printer Paper");
		private JButton refilInk = new JButton("Refil Printer Ink");
		// Bottom row of the interface:
		private JPanel bottom = new JPanel();
		private JMenu refilCoins = new JMenu("Refil Coins");
		private JMenu refilBanknotes = new JMenu("Refil Banknotes");
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
			middle.add(refilPaper);
			middle.add(refilInk);
			border.addLayoutComponent(middle, BorderLayout.CENTER);
			// Setup the bottom row UI:
			refilCoins.add("$0.05");
			refilCoins.add("$0.10");
			refilCoins.add("$0.25");
			refilCoins.add("$1.00");
			refilCoins.add("$2.00");
			refilBanknotes.add("$5.00");
			refilBanknotes.add("$10.00");
			refilBanknotes.add("$20.00");
			refilBanknotes.add("$50.00");
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
			
			refilPaper.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				}});
			
			refilInk.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				}});
			
			refilCoins.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				}});
			
			refilBanknotes.addActionListener(new ActionListener() {
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
