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
	LoginScreen login = new LoginScreen();
	AttendantMainMenu main = new AttendantMainMenu(8);
//	AttendantProductLookup lookup;
	AttendantCartScreen cart = new AttendantCartScreen();
//	AttendantAlert alert;
	
	public AttendantFrame () {
		super("Self-Chckout System: Attendant");
		cardLayout.addLayoutComponent("login", login);
		cardLayout.addLayoutComponent("main", main);
//		cardLayout.addLayoutComponent("lookup", lookup);
		cardLayout.addLayoutComponent("cart", cart);
//		cardLayout.addLayoutComponent("alert", alert);
		cardLayout.show(this.getRootPane(), "login");
		this.setLayout(cardLayout);
		this.setSize(1280, 720);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
