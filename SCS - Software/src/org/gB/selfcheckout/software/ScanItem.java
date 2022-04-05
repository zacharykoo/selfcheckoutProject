package org.gB.selfcheckout.software;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.*;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.BarcodeScannerObserver;

/**
 * Scans an item and records information about the item.
 *
 * The system should never use this in payment phase.
 *
 * If scan is successful, then the item is added to the list of scanned items
 * and the weight of the scale and calculated weight of the item on the scale is
 * updated.
 *
 * If scan is unsuccessful, then the weight of the scale and the calculated
 * weight of the scale is unspecified.
 */
public class ScanItem implements BarcodeScannerObserver {
	private State state;  // The state of the self checkout station's software.
	private boolean enabled; // Indicates whether the watched device is enabled.

	/**
	 * Initializes with system state, a barcode scanner, and a scale but no item.
	 *
	 * @param system
	 * 		Access to system state information.
	 */
	public ScanItem(State state) {
		this.state = state;
		this.enabled = true;
	}

	/*
	 * Called when the barcode scanner slot is enabled.
	 * 
	 * @param device
	 * 		The coin slot being enabled.
	 */
	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		this.enabled = true;
	}

	/*
	 * Called when the barcode scanner slot is enabled.
	 * 
	 * @param device
	 * 		The coin slot being enabled.
	 */
	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		this.enabled = false;
	}
	
	/**
	 * Adds an instance of the scanned item to the scanned item list in the
	 * software state (if the barcode scanner is enabled). Sets a flag for the
	 * electronic scale to enable the barcode scanners when the correct weight
	 * is detected. The barcode scanners are disabled until such a time.
	 * 
	 * @param barcodeScanner
	 * 		The barcode scanner instance that called this method.
	 * @param barcode
	 * 		The barcode of the scanned item.
	 */
	@Override
	public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
		if (!this.enabled) return;
		// Grab the item from the item database, if it exists.
		Item currentItem = state.idb.getItem(barcode);
		if(currentItem != null)	{ // If it does exist, expect its weight.
			state.addItem(currentItem);
			state.waitingForBagging = true;
			state.scs.mainScanner.disable();
			state.scs.handheldScanner.disable();
		
		// If the item does not exist, report the error.
		} else Main.error("Unknown item.");
	}
}
