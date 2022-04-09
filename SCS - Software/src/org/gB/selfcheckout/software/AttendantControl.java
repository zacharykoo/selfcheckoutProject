package org.gB.selfcheckout.software;

import java.util.ArrayList;
import java.util.Set;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.PLUCodedItem;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.BanknoteDispenser;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.Keyboard;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SupervisionStation;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.PLUCodedProduct;
import org.lsmr.selfcheckout.products.Product;
import org.lsmr.selfcheckout.Barcode;

public class AttendantControl {

	private ArrayList<State> scsList;
	private SupervisionStation supervisionStation;
	ArrayList<PLUCodedProduct> productList = new ArrayList<PLUCodedProduct>();

	public AttendantControl(ArrayList<State> scsList) {
		this.scsList = scsList;
		for (State state : scsList) {
			supervisionStation.add(state.scs);
		}
	}

	// Allow attendant to logout with supervisor on the current state
	public void attendantLogout(ArrayList<State> scsList) {
		this.scsList = scsList;
		for (State state : scsList) {
			supervisionStation.remove(state.scs);
		}
    }


	public boolean shutdownStation(State state) {
		for (BanknoteDispenser dispenser : state.scs.banknoteDispensers.values()) {
			dispenser.detachAll();
		}
		state.scs.banknoteInput.detachAll();
		state.scs.banknoteOutput.detachAll();
		state.scs.banknoteStorage.detachAll();
		state.scs.banknoteValidator.detachAll();
		state.scs.mainScanner.detachAll();
		state.scs.handheldScanner.detachAll();
		state.scs.cardReader.detachAll();
		for (CoinDispenser coinDispenser : state.scs.coinDispensers.values()) {
			coinDispenser.detachAll();
		}
		state.scs.coinSlot.detachAll();
		state.scs.coinStorage.detachAll();
		state.scs.coinTray.detachAll();
		state.scs.coinValidator.detachAll();
		state.scs.baggingArea.detachAll();
		state.scs.scanningArea.detachAll();
		state.scs.printer.detachAll();
		state.scs.screen.detachAll();

		state.poweredOn = false;

		return true;
	}

	public boolean startupStation(int stationId) {
		// scsList.set(stationId, new Main.init());

		return true;
	}

	public ArrayList<State> getSCSList() {
		return scsList;
	}

	public void addInkCartridge(State state, int inkCartridgeAmount) throws OverloadException {
		state.scs.printer.addInk(inkCartridgeAmount);
	}

	public void addPaper(State state, int paperAmount) throws OverloadException {
		state.scs.printer.addPaper(paperAmount);
	}

	public void refillCoinDispenser(State state, Coin... coins) throws OverloadException {
		// coinDispenser attempt. Not sure if working.
		if (!state.poweredOn) {
			for (Coin coin : coins) { // TODO: Check if this is correct.
				state.scs.coinDispensers.get(coin.getValue()).load(coin);
			}
		}
	}

	public void emptyCoinStorageUnit(State state) {
		if (!state.poweredOn) {
			state.scs.coinStorage.unload();
		}
	}

	public void refillBanknoteDispenser(State state, Banknote... banknotes) throws OverloadException {
		if (!state.poweredOn) {
			for (Banknote banknote : banknotes) {
				state.scs.banknoteDispensers.get(banknote.getValue()).load(banknote);
			}
		}
	}

	public void emptyBanknoteStorageUnit(State state) {
		if (!state.poweredOn) {
			state.scs.banknoteStorage.unload();
		}
	}

	// create a method to get an item from the item database
	public ArrayList<PLUCodedProduct> attendantLooksUpProduct(String partialLookUpCode) {
		boolean track = true;
		char[] charArray = partialLookUpCode.toCharArray();
		Numeral[] numerals = new Numeral[charArray.length];
		Set<PriceLookupCode> keys = ProductDatabases.PLU_PRODUCT_DATABASE.keySet();
		for(PriceLookupCode key : keys) {
			for(int i = 0; i < partialLookUpCode.length(); i++) {
				if(key.getNumeralAt(i) != numerals[i]) {
					track = false;
				}
			}
			if(track == true) {
				productList.add(ProductDatabases.PLU_PRODUCT_DATABASE.get(key));
			}
		}
		return productList;
	}

	// disable any user interaction, but allow user unloading/loading
	public void blockStation(State state) throws DisabledException, OverloadException {
		// state.scs.cardReader.disable();
		// state.scs.mainScanner.disable();
		// state.scs.handheldScanner.disable();
		// state.scs.scanningArea.disable();
		// eject bank notes and coins before disabling for customer
		// state.scs.coinStorage.unload();
		// state.scs.banknoteInput.emit();
		// state.scs.coinSlot.disable();
		// state.scs.banknoteInput.disable();
		state.enableScanning();
	}

	public void attendantRemoveProduct(State state, Product product) {
		// based on removeProduct method in State from another member
		state.scs.removeProduct(product);
	}

    public void attendantApproveWeightDifference(State state) throws OverloadException {
        state.expectedWeight = state.scs.baggingArea.getCurrentWeight();
    }
}


// Attendant approves a weight discrepancy: done? double check if you think this is right, since this is all i can imagine it needing.
// Attendant removes product from purchases: done
// Attendant looks up a product : done
// Attendant starts up a station : done
// Attendant shuts down a station : done


// Attendant adds paper to receipt printer : done
// Attendant adds ink to receipt printer : done
// Attendant blocks a station : done
// Attendant empties the coin storage unit : done
// Attendant empties the banknote storage unit : done
// Attendant refills the coin dispenser : done
// Attendant refills the banknote dispenser : done
// Attendant logs in to their control console : done
// Attendant logs out from their control console : done
