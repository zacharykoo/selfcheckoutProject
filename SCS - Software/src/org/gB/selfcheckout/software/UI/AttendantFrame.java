package org.gB.selfcheckout.software.UI;

import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

/**
 * JFrame to contain the UI used by attendants.
 */
public class AttendantFrame extends JFrame {
	private static final long serialVersionUID = 1L;
//	AttendantLogin login;
	AttendantMainMenu main = new AttendantMainMenu(8);
//	AttendantProductLookup lookup;
//	AttendantStationCart cart;
//	AttendantAlert alert;
	
	public static void main(String[] args) {
		AttendantFrame frame = new AttendantFrame();
	}
	
	public AttendantFrame () {
		super("Self-Chckout System: Attendant");
		this.add(main); //this.add(login);
		this.setSize(1280, 720);
		this.pack();
		this.setVisible(true);
		System.out.print("");
	}
}
