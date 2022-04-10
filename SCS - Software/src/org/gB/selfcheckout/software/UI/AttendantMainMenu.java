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
	private AttendantFrame attendantFrame;
	private BorderLayout mainBorder = new BorderLayout(); // Outermost layout.
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

	public AttendantMainMenu(AttendantFrame attendantFrame, int stations) {
		super();
		this.setLayout(mainBorder); // Set the outermost layout.
		// Create the tabs to manage each self-checkout station:
		for (int i = 0; i < stations; i ++) tabs.addTab(
				"Station " + Integer.toString(i + 1),
				new StationInterface(i));
		this.add(tabs, BorderLayout.NORTH);
		
		// Instantiate the navigation buttons, add them to the bottom panel:
		bottomPanel.add(logoutButton);
		bottomPanel.add(lookupButton);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		// Setup event handlers:
		logoutButton.addActionListener(e -> {
			attendantFrame.cardLayout.show(attendantFrame.getContentPane(), "login");
		});
    
		lookupButton.addActionListener(e -> {
			// TODO:
		});
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
			super();
			// Set the station number and main layout.
			stationIndex = index;
			this.setLayout(border);
			// Setup the top row UI:
			top.add(power);
			top.add(blockStation);
			top.add(viewCart);
			this.add(top, BorderLayout.NORTH);
			// Setup the middle row UI:
			middle.add(refillPaper);
			middle.add(refillInk);
			this.add(middle, BorderLayout.CENTER);
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
			this.add(bottom, BorderLayout.SOUTH);
			
			// Set up event handlers:
			power.addActionListener(e -> {
				
			});
			
			blockStation.addActionListener(e -> {
				
			});
			
			viewCart.addActionListener(e -> {
				
			});
		
			refillPaper.addActionListener(e -> {
				
			});
			
			refillInk.addActionListener(e -> {
				
			});
			
			refillCoins.addActionListener(e -> {
				
			});
			
			refillBanknotes.addActionListener(e -> {
				
			});
			
			emptyCoins.addActionListener(e -> {
				
			});
			
			emptyBanknotes.addActionListener(e -> {
				
			});
		}
	}
}
