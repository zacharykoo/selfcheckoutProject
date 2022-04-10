package org.gB.selfcheckout.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.gB.selfcheckout.software.AttendantControl;
import org.gB.selfcheckout.software.Main;
import org.gB.selfcheckout.software.State;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.BarcodeScannerObserver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AttendantControlTest {
	ArrayList<State> scsList;
	AttendantControl attendant;
	private Coin coin;

	@Before
	public void setup() throws Exception {
		scsList = new ArrayList<State>();
		for (int i = 0; i < 8; i++) {
			State state = Main.init(1000, 1);
			scsList.add(state);
		}

		attendant = new AttendantControl(scsList);
	}

	@Test
	public void testAttendantControl() {
		Assert.assertNotNull(attendant);
	}

	@Test
	public void testAttendantLogout() {
		fail("Not yet implemented");
	}

	@Test
	public void testShutdownStation() {
		class BarcodeScannerObserverStub implements BarcodeScannerObserver {
			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
				// TODO Auto-generated method stub
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
				// TODO Auto-generated method stub
			}

			@Override
			public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
				Assert.fail("Should not receive a call.");
			}
		} 

		attendant.shutdownStation(scsList.get(0));
		Barcode barcode = new Barcode(new Numeral[]{Numeral.one});
        Item item = new BarcodedItem(barcode, 1);
		scsList.get(0).scs.mainScanner.scan(item);
		Assert.assertEquals(false, scsList.get(0).poweredOn);
	}

	@Test
	public void testStartupStation() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSCSList() {
		Assert.assertEquals(scsList, attendant.getSCSList());
	}

	@Test
	public void testAddInkCartridge() throws OverloadException {
		attendant.addInkCartridge(null, 100);
	}

	@Test
	public void testAddPaper() throws OverloadException {
		attendant.addPaper(null, 100);
	}

	@Test
	public void testRefillCoinDispenser() throws OverloadException {
		BigDecimal bd = new BigDecimal(25);
		Coin coins = new Coin(bd);
		attendant.refillCoinDispenser(null, coins);
	}

	@Test
	public void testEmptyCoinStorageUnit() {
		attendant.emptyCoinStorageUnit(null);
	}

	@Test
	public void testRefillBanknoteDispenser() throws OverloadException {
		Banknote banknotes = new Banknote(null, 100);
		attendant.refillBanknoteDispenser(null, banknotes);
	}

	@Test
	public void testEmptyBanknoteStorageUnit() {
		attendant.emptyBanknoteStorageUnit(null);
	}

	@Test
	public void testAttendantLooksUpProduct() throws OverloadException {
		attendant.attendantLooksUpProduct(null);
	}

	@Test
	public void testBlockStation() {
		
	}

	@Test
	public void testAttendantRemoveProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testAttendantApproveWeightDifference() {
		fail("Not yet implemented");
	}

}
