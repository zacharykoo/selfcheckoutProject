package org.gB.selfcheckout.software.UI;

public class Main {
	public static void main(String[] args) {
		
		AttendantFrame attendantFrame = new AttendantFrame(8);

		CustomerFrame customerFrame = new CustomerFrame(0);
		customerFrame.setSize(500,400);
	}
}
