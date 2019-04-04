package com.assignment.alt_shift_cs991.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for Shift class
 */
public class ShiftTest {

    /**
     * Tests the constructor and all getters
     */
    @Test
    public void testConstructorAndGetters() {
        String date = "dummy date";
        Shifter shifter = new Shifter("123", "456", "bob", "smith");
        Shift shift = new Shift(date, shifter);
        assertEquals("Date test failed", date, shift.getDate());
        assertEquals("Shifter test failed", shifter, shift.getShifter());
    }

    /**
     * Tests the all setters and all getters
     */
    @Test
    public void testSettersAndGetters() {
        String date = "dummy date 1";
        Shifter shifter1 = new Shifter("123", "456", "bob", "smith");
        Shift shift = new Shift(date, shifter1);
        String date2 = "dummy date 2";
        Shifter shifter2 = new Shifter("abc", "def", "tam", "boyle");
        shift.setDate(date2);
        shift.setShifter(shifter2);
        assertEquals("Date test failed", date2, shift.getDate());
        assertEquals("Shifter test failed", shifter2, shift.getShifter());
    }

}