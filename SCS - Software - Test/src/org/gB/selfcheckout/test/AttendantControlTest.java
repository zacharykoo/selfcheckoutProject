package org.gB.selfcheckout.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.gB.selfcheckout.software.AttendantControl;
import org.gB.selfcheckout.software.Main;
import org.gB.selfcheckout.software.State;
import org.junit.Before;
import org.junit.Test;

public class AttendantControlTest {
	private AttendantControl attendant;

	@Before
	public void setup() throws Exception {
		ArrayList<State> scsList = new ArrayList<State>();
		for (int i = 0; i < 8; i++) {
			State state = Main.init(1000, 1);
			scsList.add(state);
		}

		attendant = new AttendantControl(scsList);
	}

	@Test
	public void testAttendantControl() {
		// assert.assertNotNull(attendant);
	}

	@Test
	public void testAttendantLogout() {
		fail("Not yet implemented");
	}

	@Test
	public void testShutdownStation() {
		fail("Not yet implemented");
	}

	@Test
	public void testStartupStation() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSCSList() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddInkCartridge() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPaper() {
		fail("Not yet implemented");
	}

	@Test
	public void testRefillCoinDispenser() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmptyCoinStorageUnit() {
		fail("Not yet implemented");
	}

	@Test
	public void testRefillBanknoteDispenser() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmptyBanknoteStorageUnit() {
		fail("Not yet implemented");
	}

	@Test
	public void testAttendantLooksUpProduct() {
		fail("Not yet implemented");
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
