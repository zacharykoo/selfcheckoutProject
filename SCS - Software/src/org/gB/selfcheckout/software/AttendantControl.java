package org.gB.selfcheckout.software;

import java.util.ArrayList;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.PLUCodedItem;
import org.lsmr.selfcheckout.devices.BanknoteDispenser;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SupervisionStation;
import org.lsmr.selfcheckout.products.PLUCodedProduct;
import org.lsmr.selfcheckout.Barcode;

public class AttendantControl {

    private ArrayList<State> scsList;
    private SupervisionStation supervisionStation;

    public AttendantControl(ArrayList<State> scsList) {
        this.scsList = scsList;
        for (State state : scsList) {
            supervisionStation.add(state.scs);
        }
    }


    public boolean shutdownStation(State state) {
        for (BanknoteDispenser dispenser : state.scs.banknoteDispensers.values()) { dispenser.detachAll(); }
        state.scs.banknoteInput.detachAll();
        state.scs.banknoteOutput.detachAll();
        state.scs.banknoteStorage.detachAll();
        state.scs.banknoteValidator.detachAll();
        state.scs.mainScanner.detachAll();
        state.scs.handheldScanner.detachAll();
        state.scs.cardReader.detachAll();
        for (CoinDispenser coinDispenser : state.scs.coinDispensers.values()) { coinDispenser.detachAll(); }
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

    public void refillCoinDispenser(State state, Coin... coins ) throws OverloadException {
      // coinDispenser attempt. Not sure if working.
      if (!state.poweredOn) {
        for (Coin coin : coins) { // TODO: Check if this is correct.
          state.scs.coinDispensers.get(coin.getValue()).load(coin);
        }
      }
    }

    public void emptyCoinStorageUnit(State state) {
        if (!state.poweredOn) { state.scs.coinStorage.unload(); }
    }

    public void refillBanknoteDispenser(State state, Banknote... banknotes) throws OverloadException {
        if (!state.poweredOn) {
            for (Banknote banknote : banknotes) {
                state.scs.banknoteDispensers.get(banknote.getValue()).load(banknote);
            }
        }
    }

    public void emptyBanknoteStorageUnit(State state) {
        if (!state.poweredOn) { state.scs.banknoteStorage.unload(); }
    }


    //create a method to get an item from the item database
    public void itemLookup(State state, PLUCodedProduct product) {
    	// TODO: figure what the concept of this one is
    }

    // disable any user interaction, but allow user unloading/loading
    public void blockStation(State state) throws DisabledException, OverloadException {
        	state.scs.cardReader.disable();
        	state.scs.mainScanner.disable();
        	state.scs.handheldScanner.disable();
        	state.scs.scanningArea.disable();
        	// eject bank notes and coins before disabling for customer
        	state.scs.coinStorage.unload();
        	state.scs.banknoteInput.emit();
        	state.scs.coinSlot.disable();
        	state.scs.banknoteInput.disable();
    }

	// Allow attendant to login with supervisor on the current state
    public void attendantLogin(SupervisionStation supervisor, State state) {
    	// if there are other supervisor stations, remove them
        if (supervisor.supervisedStationCount() >= 1) {
        	// not too sure if this is the correct logic
            for (SelfCheckoutStation stations : supervisor.supervisedStations() ){
                supervisor.supervisedStations().remove(stations);
            }
        }
        // add the current state to supervisor login
        supervisor.add(state.scs);
    }


}






// Attendant approves a weight discrepancy:
// Attendant removes product from purchases
// Attendant looks up a product : work in progress
// Attendant adds paper to receipt printer : done
// Attendant adds ink to receipt printer : done
// Attendant blocks a station : done..?
// Attendant empties the coin storage unit : done
// Attendant empties the banknote storage unit : done
// Attendant refills the coin dispenser : done
// Attendant refills the banknote dispenser : done
// Attendant logs in to their control console
// Attendant logs out from their control console
// Attendant starts up a station : in-progress
// Attendant shuts down a station : in-progress
