package org.gB.selfcheckout.software.frontend;

public class Main {
	public static CustomerUI customerFrame;
	
	public static void main(String[] args) {
		customerFrame = new CustomerUI("Station 1");
		customerFrame.setSize(500,400);
	}
}
