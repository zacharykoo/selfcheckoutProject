package org.gB.selfcheckout.software.UI;

import javax.swing.JFrame;

/**
 * JFrame to contain the UI used by attendants.
 */
public class AttendantFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	AttendantLogin login;
	AttendantMainMenu main;
	AttendantProductLookup lookup;
	AttendantStationCart cart;
	AttendantAlert alert;
	
	public AttendantFrame () {
		super("Self-Chckout System: Attendant");
		this.add(login);
		this.setVisible(true);
	}
}
