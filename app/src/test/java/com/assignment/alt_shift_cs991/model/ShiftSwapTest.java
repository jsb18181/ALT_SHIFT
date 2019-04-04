package com.assignment.alt_shift_cs991.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShiftSwapTest {

    @Test
    public void testConstructorAndGetters() {
        String date1 = "dummy date 1";
        String date2 = "dummy date 2";
        Shifter shifter1 = new Shifter("123", "456", "bob", "smith");
        Shifter shifter2 = new Shifter("abc", "def", "tam", "boyle");
        Shift shift1 = new Shift(date1, shifter1);
        Shift shift2 = new Shift(date2, shifter2);
        ShiftSwap shiftSwap = new ShiftSwap(shift1, shift2);
        assertEquals("UnwantedShift test failed ", shift1, shiftSwap.getUnwantedShift());
        assertEquals("WantedShift test failed ", shift2, shiftSwap.getWantedShift());
        assertEquals("Status test failed ", 0, shiftSwap.getStatus());
    }

    @Test
    public void testSettersAndGetters() {
        String date1 = "dummy date 1";
        String date2 = "dummy date 2";
        Shifter shifter1 = new Shifter("123", "456", "bob", "smith");
        Shifter shifter2 = new Shifter("abc", "def", "tam", "boyle");
        Shift shift1 = new Shift(date1, shifter1);
        Shift shift2 = new Shift(date2, shifter2);
        ShiftSwap shiftSwap = new ShiftSwap(shift1, shift2);
        String date3 = "dummy date 3";
        String date4 = "dummy date 4";
        Shifter shifter3 = new Shifter("ggg", "jjj", "alice", "smith");
        Shifter shifter4 = new Shifter("ABC", "DEF", "sue", "boyle");
        Shift shift3 = new Shift(date3, shifter3);
        Shift shift4 = new Shift(date4, shifter4);
        shiftSwap.setUnwantedShift(shift3);
        shiftSwap.setWantedShift(shift4);
        assertEquals("UnwantedShift test failed ", shift3, shiftSwap.getUnwantedShift());
        assertEquals("WantedShift test failed ", shift4, shiftSwap.getWantedShift());
        shiftSwap.setStatus(-1);
        assertEquals("Set status test failed ", -1, shiftSwap.getStatus());
        shiftSwap.setStatusPending();
        assertEquals("Set status pending test failed ", 0, shiftSwap.getStatus());
        shiftSwap.setStatusAccepted();
        assertEquals("Set status accepted test failed ", 1, shiftSwap.getStatus());
        shiftSwap.setStatusRejected();
        assertEquals("Set status rejected test failed ", -1, shiftSwap.getStatus());
    }

    @Test
    public void testEquals() {
        String date1 = "dummy date 1";
        Shifter shifter1 = new Shifter("123", "456", "bob", "smith");
        String date2 = "dummy date 2";
        Shifter shifter2 = new Shifter("123", "456", "bob", "smith");
        Shift shift1 = new Shift(date1, shifter1);
        Shift shift2 = new Shift(date2, shifter2);
        ShiftSwap shiftSwap1 = new ShiftSwap(shift1, shift2);
        ShiftSwap shiftSwap2 = new ShiftSwap(shift1, shift2);
        ShiftSwap shiftSwap3 = new ShiftSwap(shift2, shift2);
        ShiftSwap shiftSwap4 = new ShiftSwap(shift1, shift1);
        ShiftSwap shiftSwap5 = new ShiftSwap(shift2, shift1);
        assertTrue("1st Equals test failed ", shiftSwap1.equals(shiftSwap2));
        assertFalse("2nd Equals test failed ", shiftSwap1.equals(shiftSwap3));
        assertFalse("3rd Equals test failed ", shiftSwap1.equals(shiftSwap4));
        assertFalse("4th Equals test failed ", shiftSwap1.equals(shiftSwap5));
    }

}