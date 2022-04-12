package org.gB.selfcheckout.software;

import java.util.ArrayList;
import java.util.Set;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.BanknoteDispenser;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SupervisionStation;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.PLUCodedProduct;
import org.lsmr.selfcheckout.products.Product;

public class AttendantControl {

	private ArrayList<State> scsList;
	private SupervisionStation supervisionStation;
	ArrayList<PLUCodedProduct> productList = new ArrayList<PLUCodedProduct>();

	public AttendantControl(ArrayList<State> scsList) {
		this.scsList = scsList;
		supervisionStation = new SupervisionStation();
		for (State state : this.scsList) {
			supervisionStation.add(state.scs);
		}
	}

	// Allow attendant to logout with supervisor on the current state
	public boolean logout() {
		for (State state : this.scsList) {
			supervisionStation.remove(state.scs);
		}
		return true;
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

	public boolean startupStation(int stationId, int scaleMaxWeight, int scaleSensitivity) throws Exception {
		new Main();
		this.scsList.set(stationId, Main.init(scaleMaxWeight, scaleSensitivity));

		return true;
	}

	public ArrayList<State> getSCSList() {
		return this.scsList;
	}

	public boolean addInkCartridge(State state, int inkCartridgeAmount) throws OverloadException {
		state.charactersOfInkRemaining += inkCartridgeAmount;
		state.scs.printer.addInk(inkCartridgeAmount);
		return true;
	}

	public boolean addPaper(State state, int paperAmount) throws OverloadException {
		state.linesOfPaperRemaining += paperAmount;
		state.scs.printer.addPaper(paperAmount);
		return true;
	}

	public boolean refillCoinDispenser(State state, Coin... coins) throws OverloadException {
		// coinDispenser attempt. Not sure if working.
		if (!state.poweredOn) {
			for (Coin coin : coins) { // TODO: Check if this is correct.
				state.scs.coinDispensers.get(coin.getValue()).load(coin);
			}
			return true;
		}
		return false;
	}

	public boolean emptyCoinStorageUnit(State state) {
		if (!state.poweredOn) {
			state.scs.coinStorage.unload();
			return true;
		}
		return false;
	}

	public boolean refillBanknoteDispenser(State state, Banknote... banknotes) throws OverloadException {
		if (!state.poweredOn) {
			for (Banknote banknote : banknotes) {
				state.scs.banknoteDispensers.get(banknote.getValue()).load(banknote);
			}
			return true;
		}
		return false;
	}

	public boolean emptyBanknoteStorageUnit(State state) {
		if (!state.poweredOn) {
			state.scs.banknoteStorage.unload();
			return true;
		}
		return false;
	}

	// create a method to get an item from the item database
	public ArrayList<PLUCodedProduct> looksUpProduct(String partialLookUpCode) {
		productList.clear();

		boolean track = true;
		char[] charArray = partialLookUpCode.toCharArray();
		Set<PriceLookupCode> keys = ProductDatabases.PLU_PRODUCT_DATABASE.keySet();
		for(PriceLookupCode key : keys) {
			for (int i = 0; i < key.toString().length(); i++) {
				if (i < charArray.length) {
					if (key.toString().charAt(i) != charArray[i]) {
						track = false;
					}
				} else {
					break;
				}
			}
			
			if (track) {
				productList.add(ProductDatabases.PLU_PRODUCT_DATABASE.get(key));
			}
		}
		
		return productList;
	}

	// disable any user interaction, but allow user unloading/loading
	public boolean blockStation(State state) throws DisabledException, OverloadException {
		state.enableScanning();
		return true;
	}

	public boolean removeProduct(State state, Product product) {
		// based on removeProduct method in State from another member
		state.removeProduct(product);
		return true;
	}

    public boolean approveWeightDifference(State state) throws OverloadException {
        state.expectedWeight = state.scs.baggingArea.getCurrentWeight();
        state.enableScanning();
		return true;
    }
}