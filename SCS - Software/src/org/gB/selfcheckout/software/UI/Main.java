package org.gB.selfcheckout.software.UI;

public class Main {
	
	public static final AttendantFrame ATTENDANT_FRAME = new AttendantFrame();
	
	public static void main(String[] args) {		
		CustomerFrame customerFrame = new CustomerFrame(0);
		customerFrame.setSize(500,400);
	}
}
