package com.assignment.alt_shift_cs991.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for Shifter class
 */
public class ShifterTest {

    /**
     * Tests the constructor and all getters
     */
    @Test
    public void testConstructorAndGetters() {
        String userID = "123";
        String password = "456";
        String firstName = "bob";
        String surname = "smith";
        Shifter shifter = new Shifter(userID, password, firstName, surname);
        assertEquals("UserID test failed", userID, shifter.getUserID());
        assertEquals("Password test failed", password, shifter.getPassword());
        assertEquals("FirstName test failed", firstName, shifter.getFirstName());
        assertEquals("Surname test failed", surname, shifter.getSurname());
        assertEquals("Manager test failed", false, shifter.isManager());
    }

    /**
     * Tests the all setters and all getters
     */
    @Test
    public void testSettersAndGetters() {
        Shifter shifter = new Shifter("", "", "", "");
        String userID = "123";
        String password = "456";
        String firstName = "bob";
        String surname = "smith";
        boolean manager = true;
        int keyID = 1234;
        shifter.setUserID(userID);
        shifter.setPassword(password);
        shifter.setFirstName(firstName);
        shifter.setSurname(surname);
        shifter.setManager(manager);
        shifter.setKeyID(keyID);
        assertEquals("UserID test failed", userID, shifter.getUserID());
        assertEquals("Password test failed", password, shifter.getPassword());
        assertEquals("FirstName test failed", firstName, shifter.getFirstName());
        assertEquals("Surname test failed", surname, shifter.getSurname());
        assertEquals("Manager test failed", manager, shifter.isManager());
        assertEquals("Manager test failed", keyID, shifter.getKeyID());
    }

}