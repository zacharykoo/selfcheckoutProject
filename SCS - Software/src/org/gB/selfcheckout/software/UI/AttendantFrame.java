package org.gB.selfcheckout.software.UI;

import java.awt.CardLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

/**
 * JFrame to contain the UI used by attendants.
 */
public class AttendantFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public CardLayout cardLayout = new CardLayout();
	LoginScreen login = new LoginScreen(this);
	AttendantMainMenu main;
//	AttendantProductLookup lookup;
	AttendantCartScreen cart = new AttendantCartScreen();
//	AttendantAlert alert;
	

	public AttendantFrame (int numStations) {
		super("Attendant Station");
		
		main = new AttendantMainMenu(this, numStations);
		
		addPanels();
		
		cardLayout.show(this.getContentPane(), "login");
		this.setLayout(cardLayout);
		this.setSize(1280, 720);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	private void addPanels() {
		
		this.getContentPane().setLayout(cardLayout);

		getContentPane().add(login, "login");
		getContentPane().add(main, "main");
		getContentPane().add(cart, "cart");

//		getContentPane().add(alert, "alert");
//		getContentPane().add(lookup, "lookup");
	}
}
