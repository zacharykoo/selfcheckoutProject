package org.gB.selfcheckout.software.UI;

import java.awt.CardLayout;
import java.util.List;

import javax.swing.JFrame;

import org.gB.selfcheckout.software.AttendantControl;
import org.gB.selfcheckout.software.State;

/**
 * JFrame to contain the UI used by attendants.
 */
public class AttendantFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	List<State> states;
	List<CustomerFrame> cFrames;
	AttendantControl ac;
	public CardLayout cardLayout = new CardLayout();
	LoginScreen login = new LoginScreen(this);
	AttendantMainMenu main;
	AttendantLookupProduct lookup = new AttendantLookupProduct(this);;
	AttendantCartScreen cart = new AttendantCartScreen();
	AttendantStationShutDown shutDown;
	AlertPage alert;

	public AttendantFrame (List<State> states, List<CustomerFrame> cFrames, AttendantControl ac) {
		super("Attendant Station");
		this.states = states;
		this.cFrames = cFrames;
		this.ac = ac;
		main = new AttendantMainMenu(this, states);
		alert = new AlertPage(this);
		shutDown = new AttendantStationShutDown(this);
		
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
		getContentPane().add(shutDown, "shutDown");
		getContentPane().add(lookup, "lookup");
		getContentPane().add(alert, "alert");
	}
}
