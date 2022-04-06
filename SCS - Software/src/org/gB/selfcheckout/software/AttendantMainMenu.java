package org.gB.selfcheckout.software;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class AttendantMainMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	private BorderLayout border;
	
	private JPanel bottomPanel;
	private JButton logoutButton;
	private JButton lookupButton;
	
	private JTabbedPane tabs;
	
	public AttendantMainMenu() {
		this.setLayout(border);
		
		logoutButton = new JButton("Logout");
		lookupButton = new JButton("Lookup Product");
		bottomPanel.add(logoutButton);
		bottomPanel.add(lookupButton);
		border.addLayoutComponent(bottomPanel, BorderLayout.SOUTH);
		
		tabs = new JTabbedPane();
		for (int i = 0; i < 8; i ++) tabs.add(
				"Station " + Integer.toString(i + 1),
				new StationInterface(i + 1));
		border.addLayoutComponent(tabs, BorderLayout.SOUTH);
	}
	
	private class StationInterface extends JPanel {
		private static final long serialVersionUID = 1L;
		private BorderLayout border;
		private JPanel top;
		private JButton power;
		private JButton blockStation;
		private JButton viewCart;
		private JPanel middle;
		private JButton refilPaper;
		private JButton refilInk;
		private JPanel bottom;
		private JMenu refilCoins;
		private JMenu refilBanknotes;
		private JButton emptyCoins;
		private JButton emptyBanknotes;

		public StationInterface(int number) {
			border = new BorderLayout();
			
			top = new JPanel();
			power = new JButton("Toggle Station Power");
			top.add(power);
			blockStation = new JButton("Block Station");
			top.add(blockStation);
			viewCart = new JButton("View Scanned Items");
			top.add(viewCart);
			border.addLayoutComponent(top, BorderLayout.NORTH);
			
			middle = new JPanel();
			refilPaper = new JButton("Refil Printer Paper");
			middle.add(refilPaper);
			refilInk = new JButton("Refil Printer Ink");
			middle.add(refilInk);
			border.addLayoutComponent(middle, BorderLayout.CENTER);
			
			bottom = new JPanel();
			refilCoins = new JMenu("Refil Coins");
			refilCoins.add("$0.05");
			refilCoins.add("$0.10");
			refilCoins.add("$0.25");
			refilCoins.add("$1.00");
			refilCoins.add("$2.00");
			refilBanknotes = new JMenu("Refil Banknotes");
			refilBanknotes.add("$5.00");
			refilBanknotes.add("$10.00");
			refilBanknotes.add("$20.00");
			refilBanknotes.add("$50.00");
			emptyCoins = new JButton("Empty Coin Storage");
			middle.add(emptyCoins);
			emptyBanknotes = new JButton("Empty Banknote Storage");
			middle.add(emptyBanknotes);
			border.addLayoutComponent(bottom, BorderLayout.SOUTH);
		}
	}
}
