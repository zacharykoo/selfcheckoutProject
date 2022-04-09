package org.gB.selfcheckout.software.UI;

public class Main {
	public static CustomerFrame customerFrame;
	
	public static void main(String[] args) {		
		customerFrame = new CustomerFrame(0);
		customerFrame.setSize(500,400);
	}
}
