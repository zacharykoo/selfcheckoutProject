package org.gB.selfcheckout.software;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.TouchScreenObserver;

public class CustomerReturnsToAddingItems implements TouchScreenObserver{
	private State state;  // The state of the self checkout station's software.
	private boolean enabled; // Indicates whether the watched device is enabled.
	private boolean returnToScan; // Indicates that the user would like to return to scanning items.

	
	public CustomerReturnsToAddingItems(State state) {
		this.state = state;
		this.enabled = true;
		this.returnToScan = false;
		
	}
	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		this.enabled = true;
		this.returnToScan = true;
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		this.enabled = false;
	}
	

}
