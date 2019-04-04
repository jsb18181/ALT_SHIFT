package com.assignment.alt_shift_cs991.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit tests for ShiftManager
 */
public class ShiftManagerTest {

    private ShiftManager shiftManager;
    private Shifter shifter1;
    private Shifter shifter2;
    private Shift shift1;
    private Shift shift2;
    private ShiftSwap shiftSwap;

    /**
     * Sets up the shiftManager with shifts, shifers and shiftSwaps
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        shiftManager = new ShiftManager();
        shifter1 = new Shifter("1", "1", "f", "s");
        shifter2 = new Shifter("2", "2", "f", "s");
        shift1 = new Shift("date1", shifter1);
        shift2 = new Shift("date2", shifter2);
        shiftSwap = new ShiftSwap(shift1, shift2);
        shiftManager.addShifter(shifter1);
        shiftManager.addShifter(shifter2);
        shiftManager.addShift(shift1);
        shiftManager.addShift(shift2);
        shiftManager.addShiftSwap(shiftSwap);
    }

    /**
     * Tests ShiftManager Getters for shiftManager lists
     */
    @Test
    public void testGetters() {
        assertEquals(shiftManager.getShifters().size(), 2);
        assertEquals(shiftManager.getShifts().size(), 2);
        assertEquals(shiftManager.getShiftSwaps().size(), 1);
    }

    /**
     * Tests ShiftManager Setters for shiftManager lists
     */
    @Test
    public void testSetters() {
        ArrayList<Shifter> newShifters = new ArrayList<Shifter>();
        ArrayList<Shift> newShifts = new ArrayList<Shift>();
        ArrayList<ShiftSwap> newSwaps = new ArrayList<ShiftSwap>();
        ShiftSwap swap = new ShiftSwap(shift2, shift1);

        newShifters.add(shifter2);
        newShifts.add(shift2);
        newSwaps.add(swap);

        shiftManager.setShifters(newShifters);
        assertEquals(shiftManager.getShifters().get(0), shifter2);
        shiftManager.setShifts(newShifts);
        assertEquals(shiftManager.getShifts().get(0), shift2);
        shiftManager.setShiftSwaps(newSwaps);
        assertEquals(shiftManager.getShiftSwaps().get(0), swap);

    }

    /**
     * Tests getShifterLogin when searching with correct details
     */
    @Test
    public void testGetShifterLoginSuccess() {
        assertEquals(shiftManager.getShifterLogin("1", "1"), "f");
    }

    /**
     * Tests getShifterLogin when searching with incorrect details
     */
    @Test
    public void testGetShifterLoginNull() {
        assertNull(shiftManager.getShifterLogin("x", "y"));
    }

    /**
     * Tests getShifter when searching with correct details
     */
    @Test
    public void testGetShifterSuccess() {
        assertEquals(shiftManager.getShifter("1", "1"), shifter1);
    }

    /**
     * Tests getShifter when searching with incorrect details
     */
    @Test
    public void testGetShifterNull() {
        assertNull(shiftManager.getShifter("x", "y"));
    }

    /**
     * Tests getNumberShifters
     */
    @Test
    public void testGetNumberShifter() {
        assertEquals(shiftManager.getNumberShifters(), 2);
    }

    /**
     * Tests getMyShifts
     */
    @Test
    public void testGetMyShifts() {
        assertEquals(shiftManager.getMyShifts(shifter1).get(0), shift1);
    }

    /**
     * Tests getMyShiftsByDate
     */
    @Test
    public void testGetMyShiftsByDate() {
        assertEquals(shiftManager.getMyShiftsByDate(shifter1, "date1").get(0), shift1);
    }

    /**
     * Tests getMyShiftsDates
     */
    @Test
    public void testGetMyShiftsDates() {
        assertEquals(shiftManager.getMyShiftsDates(shifter1).get(0), "date1");
    }

    /**
     * Tests getAllShiftsDates
     */
    @Test
    public void testGetAllShiftsDates() {
        assertEquals(shiftManager.getAllShiftsDates().get(0), "date1");
    }

    /**
     * Tests getAllShiftsByDates
     */
    @Test
    public void testGetAllShiftsByDates() {
        assertEquals(shiftManager.getAllShiftsByDate("date1").get(0), shift1);
    }

    /**
     * Tests getSwapableShifts
     */
    @Test
    public void testGetSwapableShifts() {
        assertEquals(shiftManager.getSwapableShifts(shift1).get(0), shift2);
    }

    /**
     * Tests getRequestedSwaps
     */
    @Test
    public void testGetRequestedSwaps() {
        assertEquals(shiftManager.getRequestedSwaps(shifter1).get(0), shiftSwap);
    }

    /**
     * Tests getShift
     */
    @Test
    public void testGetShift() {
        assertEquals(shiftManager.getShift(shifter1, "date1"), shift1);
    }

    /**
     * Tests addShifter(username, password, firstname, surname) when shifter has not already been created
     */
    @Test
    public void testAddShifterSuccess() {
        assertTrue(shiftManager.addShifter("x", "x", "x", "x"));
        assertEquals(shiftManager.getShifters().size(), 3);
    }

    /**
     * Tests addShifter(username, password, firstname, surname) when shifter has already been created
     */
    @Test
    public void testAddShifterFail() {
        assertFalse(shiftManager.addShifter("1", "1", "x", "x"));
        assertEquals(shiftManager.getShifters().size(), 2);
    }

    /**
     * Tests addShifter(shifter)
     */
    @Test
    public void testAddShifterShifter() {
        Shifter s = new Shifter("x", "x", "x", "x");
        shiftManager.addShifter(s);
        assertEquals(shiftManager.getShifters().size(), 3);
    }

    /**
     * Tests addShift(shift)
     */
    @Test
    public void testAddShiftShift() {
        shiftManager.addShift(shift1);
        assertEquals(shiftManager.getShifts().size(), 3);
    }

    /**
     * Tests addShift(date, shifter)
     */
    @Test
    public void testAddShiftShifter() {
        shiftManager.addShift("date3", shifter1);
        assertEquals(shiftManager.getShifts().size(), 3);
    }

    /**
     * Tests addShiftSwap when shiftSwap hasn't been created already
     */
    @Test
    public void testAddShiftSwapSuccess() {
        ShiftSwap s = new ShiftSwap(shift2, shift1);
        assertTrue(shiftManager.addShiftSwap(s));
    }

    /**
     * Tests addShiftSwap when shiftSwap has been created already
     */
    @Test
    public void testAddShiftSwapFail() {
        assertFalse(shiftManager.addShiftSwap(shiftSwap));
    }

    /**
     * Tests removeSwap
     */
    @Test
    public void testRemoveSwap() {
        shiftManager.removeSwap(shiftSwap);
        assertEquals(shiftManager.getShiftSwaps().size(), 0);
    }

    /**
     * Tests swapShifts
     */
    @Test
    public void testSwapShifts() {
        shiftManager.swapShifts(shiftSwap);
        assertEquals(shiftManager.getShifts().get(0).getShifter(), shifter2);
        assertEquals(shiftManager.getShifts().get(1).getShifter(), shifter1);
    }

    /**
     * Tests dateFormatterStd when successful
     */
    @Test
    public void testDateFormatterStdSuccess() {

    }

    /**
     * Tests dateFormatterStd when unsuccessful
     */
    @Test
    public void testDateFormatterStdFail() {

    }

    /**
     * Tests dateFormatterCon when successful
     */
    @Test
    public void testDateFormatterConSuccess() {

    }

    /**
     * Tests dateFormatterCon when unsuccessful
     */
    @Test
    public void testDateFormatterConFail() {

    }

}