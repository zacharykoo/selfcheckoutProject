package org.gB.selfcheckout.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

import org.gB.selfcheckout.software.AttendantControl;
import org.gB.selfcheckout.software.ItemDatabase;
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
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.BarcodeScannerObserver;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AttendantControlTest {
	private ArrayList<State> scsList;
	private AttendantControl attendant;

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
		Assert.assertTrue(attendant.logout());
	}

	@Test
	public void testShutdownStation() {
		class BarcodeScannerObserverStub implements BarcodeScannerObserver { // TODO: Fix this stub, doesn't work.
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
		// scsList.get(0).scs.mainScanner.attach(BarcodeScannerObserverStub);

		Assert.assertTrue(attendant.shutdownStation(scsList.get(0)));
		Barcode barcode = new Barcode(new Numeral[]{Numeral.one});
        Item item = new BarcodedItem(barcode, 1);
		scsList.get(0).scs.mainScanner.scan(item);
		Assert.assertEquals(false, scsList.get(0).poweredOn);
	}

	@Test
	public void testStartupStation() {
		try {
			Assert.assertTrue(attendant.startupStation(5, 100, 1));
			Assert.assertTrue(attendant.getSCSList().get(5).poweredOn);
		} catch(Exception e) {
			Assert.fail("Should not throw an exception.");
		}
	}

	@Test
	public void testGetSCSList() {
		Assert.assertEquals(scsList, attendant.getSCSList());
	}

	@Test
	public void testAddInkCartridge() throws OverloadException {
		State state = scsList.get(0);
		Assert.assertTrue(attendant.addInkCartridge(state, 100));
	}

	@Test
	public void testAddPaper() throws OverloadException {
		State state = scsList.get(0);
		Assert.assertTrue(attendant.addPaper(state, 10));
	}

	@Test
	public void testRefillCoinDispenser() throws OverloadException {
		State state = scsList.get(0);
		state.poweredOn = false;
		BigDecimal CoinValue = new BigDecimal(0.25);
		Coin coin = new Coin(CoinValue);
		Assert.assertTrue(attendant.refillCoinDispenser(state, coin));
		state.poweredOn = true;
		Assert.assertFalse(attendant.refillCoinDispenser(state, coin));
	}

	@Test
	public void testEmptyCoinStorageUnit() {
		State state = scsList.get(0);
		state.poweredOn = false;
		Assert.assertTrue(attendant.emptyCoinStorageUnit(state));
		state.poweredOn = true;
		Assert.assertFalse(attendant.emptyCoinStorageUnit(state));
	}

	@Test
	public void testRefillBanknoteDispenser() throws OverloadException {
		State state = scsList.get(0);
		state.poweredOn = false;
		Currency currency = Currency.getInstance("USD");
		Banknote banknotes = new Banknote(currency, 50);
		Assert.assertTrue(attendant.refillBanknoteDispenser(state, banknotes));
		state.poweredOn = true;
		Assert.assertFalse(attendant.refillBanknoteDispenser(state, banknotes));
	}

	@Test
	public void testEmptyBanknoteStorageUnit() {
		State state = scsList.get(0);
		state.poweredOn = false;
		Assert.assertTrue(attendant.emptyBanknoteStorageUnit(state));
		state.poweredOn = true;
		Assert.assertFalse(attendant.emptyBanknoteStorageUnit(state));
	}

	@Test
	public void testAttendantLooksUpProduct() throws OverloadException {
		State state = scsList.get(0);
		ItemDatabase itemDatabase = new ItemDatabase();
		attendant.attendantLooksUpProduct(null);
	}

	@Test
	public void testBlockStation() throws DisabledException, OverloadException {
		State state = scsList.get(0);
		Assert.assertTrue(attendant.blockStation(state));
		
	}

	@Test
	public void testAttendantRemoveProduct() {
		Numeral[] temp = {Numeral.one,Numeral.two,Numeral.three};
		State state = scsList.get(0);
		Barcode bc1 = new Barcode(temp);
		BarcodedProduct product = new BarcodedProduct(bc1, "test", new BigDecimal(10), 0.1);
		Assert.assertTrue(attendant.attendantRemoveProduct(state, product));
	}

	@Test
	public void testAttendantApproveWeightDifference() throws OverloadException {
		State state = scsList.get(0);
		Assert.assertTrue(attendant.attendantApproveWeightDifference(state));
	}

}
