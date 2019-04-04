package com.assignment.alt_shift_cs991.model;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Holds lists of Shifters, Shifts and ShiftSwaps
 */
public class ShiftManager implements Serializable {

    private List<Shifter> shifters;
    private List<Shift> shifts;
    private List<ShiftSwap> shiftSwaps;

    /**
     * Constructor for ShiftManager
     */
    public ShiftManager() {
        shifters = new ArrayList<>();
        shifts = new ArrayList<>();
        shiftSwaps = new ArrayList<>();
    }

    /**
     * Getter for list of Shifters
     *
     * @return list of Shifters
     */
    public List<Shifter> getShifters() {
        return shifters;
    }

    /**
     * Setter for list of Shifters
     *
     * @param shifters list of Shifters
     */
    public void setShifters(List<Shifter> shifters) {
        this.shifters = shifters;
    }

    /**
     * Returns the list of all Shifts
     *
     * @return
     */
    public List<Shift> getShifts() {
        return shifts;
    }

    /**
     * Setter for list of shifts
     *
     * @param shifts
     */
    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }

    /**
     * Getter for list of ShiftSwaps
     *
     * @return list of ShiftSwaps
     */
    public List<ShiftSwap> getShiftSwaps() {
        return shiftSwaps;
    }

    /**
     * Setter for list of shiftSwaps
     *
     * @param shiftSwaps
     */
    public void setShiftSwaps(List<ShiftSwap> shiftSwaps) {
        this.shiftSwaps = shiftSwaps;
    }

    /**
     * getShifter searches in the database of Shifters for the shifter with the given user id and password,
     * returns a reference to the shifter if found and null if not found.
     *
     * @param userID   User ID of shifter or null.
     * @param password Password of shifter.
     * @return Shifter with given user ID and password.
     */
    public String getShifterLogin(String userID, String password) {
        for (Shifter s : shifters) {
            if (s.getUserID().equals(userID) && s.getPassword().equals(password)) {
                return s.getFirstName();
            }
        }
        return null;
    }

    /**
     * getShifter searches in the database of Shifters for the shifter with the given user id and password,
     * returns a reference to the shifter if found and null if not found.
     *
     * @param u User ID of shifter or null.
     * @param p Password of shifter.
     * @return Shifter with given user ID and password.
     */
    public Shifter getShifter(String u, String p) {
        for (Shifter s : shifters) {
            if ((s.getUserID().equals(u)) && (s.getPassword().equals(p))) {
                return s;
            }
        }
        return null;
    }

    /**
     * Returns the number of Shifters stored in the backend
     *
     * @return numer of shifters
     */
    public int getNumberShifters() {
        return shifters.size();
    }


    /**
     * getShifts(Shifter) returns a list of the given shifter's shifts.
     *
     * @param shifter A shifter.
     * @return The shifter's list of shifts.
     */
    public List<Shift> getMyShifts(Shifter shifter) {
        // code to get a list of the given shifter's shifts
        List<Shift> myShifts = new ArrayList<Shift>();
        for (Shift shift : shifts) {
            //System.out.println("" + );
            //if (shifter.getFirstName().equals(shift.getShifter().getFirstName())){
            if (shifter.equals(shift.getShifter())) {
                myShifts.add(shift);
            }
        }
        return myShifts;
    }

    /**
     * Returns a list of Shifts of a particular Shifter on a date
     *
     * @param shifter
     * @param date
     * @return
     */
    public List<Shift> getMyShiftsByDate(Shifter shifter, String date) {
        List<Shift> myShiftsByDate = new ArrayList<Shift>();
        for (Shift shift : shifts) {
            //System.out.println("" + );
            if (shifter.equals(shift.getShifter()) && shift.getDate().equals(date)) {
                myShiftsByDate.add(shift);
            }
        }
        return myShiftsByDate;
    }

    /**
     * Returns a list of dates that Shifter has Shifts on
     *
     * @param shifter
     * @return list of shifts
     */
    public List<String> getMyShiftsDates(Shifter shifter) {
        List<String> myShiftsDates = new ArrayList<String>();
        for (Shift shift : shifts) {
            //System.out.println("" + );
            if (shifter.equals(shift.getShifter())) {
                myShiftsDates.add(shift.getDate());
            }
        }
        return myShiftsDates;
    }

    /**
     * Returns a list of dates for all shifts
     *
     * @return list of dates
     */
    public List<String> getAllShiftsDates() {
        List<String> allShiftsDates = new ArrayList<String>();
        for (Shift shift : shifts) {
            allShiftsDates.add(shift.getDate());
        }
        return allShiftsDates;
    }

    /**
     * Returns a list of all shifts for a specified date
     *
     * @param date
     * @return list of shifts on date
     */
    public List<Shift> getAllShiftsByDate(String date) {
        List<Shift> allShiftsByDate = new ArrayList<Shift>();
        for (Shift shift : shifts) {
            if (shift.getDate().equals(date)) {
                allShiftsByDate.add(shift);
            }
        }
        return allShiftsByDate;
    }

    /**
     * getSwapableShifts() returns a list of shifts that can be swapped with the given shift,
     * according to certain rules.
     *
     * @param unwantedShift A shift.
     * @return The list of shifts that are swapable with the given shift.
     */
    public List<Shift> getSwapableShifts(Shift unwantedShift) {
        List<Shift> myShifts = getMyShifts(unwantedShift.getShifter());
        List<Shift> swapableShifts = new ArrayList<Shift>();
        for (Shift shift : shifts) {
            if (!unwantedShift.getShifter().equals(shift.getShifter())) {
                boolean clash = false;
                for (Shift shift2 : myShifts) {
                    if (shift2.getDate().equals(shift.getDate())) {
                        clash = true;
                    }
                }
                if (!clash) swapableShifts.add(shift);
            }
        }
        return swapableShifts;
    }

    /**
     * Returns a list of shift swaps requested by the given shifter.
     *
     * @param shifter A Shifter.
     * @return The list of shift swaps requested by the shifter.
     */
    public List<ShiftSwap> getRequestedSwaps(Shifter shifter) {
        List<ShiftSwap> requestedSwaps = new ArrayList<ShiftSwap>();
        for (ShiftSwap shiftSwap : shiftSwaps) {
            if (shifter == shiftSwap.getUnwantedShift().getShifter()) requestedSwaps.add(shiftSwap);
        }
        return requestedSwaps;
    }

    /**
     * Returns the number of shift swaps available to the given shifter
     *
     * @param shifter
     * @return the number of available swaps
     */
    public int getCountAvailableSwaps(Shifter shifter) {
        int count = 0;
        for (ShiftSwap shiftSwap : shiftSwaps) {
            if (shifter == shiftSwap.getWantedShift().getShifter()) count++;
        }
        return count;
    }

    /**
     * Returns the shift swaps available to the given shifter
     *
     * @param shifter
     * @return the list of available swaps
     */
    public List<ShiftSwap> getAvailableSwaps(Shifter shifter) {
        List<ShiftSwap> availableSwaps = new ArrayList<>();
        for (ShiftSwap shiftSwap : shiftSwaps) {
            if (shifter == shiftSwap.getWantedShift().getShifter()) {
                availableSwaps.add(shiftSwap);
            }
        }
        return availableSwaps;
    }

    /**
     * Returns a shift with a particular Shifter on a date
     *
     * @param shifter
     * @param date
     * @return
     */
    public Shift getShift(Shifter shifter, String date) {
        String given = dateFormatterCon(date, "MMM dd");
        for (Shift shift : shifter.getMyShifts()) {
            String stored = dateFormatterCon(shift.getDate().toString(), "MMM dd");
            if (stored.compareTo(given) == 0) {
                return shift;
            }
        }
        return null;
    }

    /**
     * Adds a Shifter to the list of Shifters
     *
     * @param u username
     * @param p password
     * @param f first name
     * @param s surname
     * @return
     */
    public boolean addShifter(String u, String p, String f, String s) {
        if (getShifter(u, p) == null) {
            shifters.add(new Shifter(u, p, f, s));
            return true;
        }
        return false;
    }

    /**
     * Adds a Shifter to the list of Shifters
     *
     * @param s
     */
    public void addShifter(Shifter s) {
        shifters.add(s);
    }

    /**
     * Adds a Shift to the list of Shifts
     *
     * @param s shift
     */
    public void addShift(Shift s) {
        shifts.add(s);
    }

    /**
     * Adds a Shift to the list of Shifts
     *
     * @param d date
     * @param s shifter
     */
    public void addShift(String d, Shifter s) {
        shifts.add(new Shift(d, s));
    }

    /**
     * Adds a ShiftSwap to the list of ShiftSwaps
     *
     * @param s ShiftSwap
     */
    public boolean addShiftSwap(ShiftSwap s) {
        for (ShiftSwap swap : shiftSwaps) {
            if (s.equals(swap)) {
                return false;
            }
        }
        shiftSwaps.add(s);
        return true;
    }

    /**
     * Removes a shiftSwap from the list of shiftSwaps
     *
     * @param swap
     */
    public void removeSwap(ShiftSwap swap) {
        shiftSwaps.remove(swap);
    }

    /**
     * Removes a Shift from the list of Shifts
     *
     * @param shift
     */
    public void removeShift(Shift shift) {
        shifts.remove(shift);
    }


    /**
     * Swaps the shifters in the two shifts in the given shift swap
     *
     * @param s A shift swap.
     */
    public void swapShifts(ShiftSwap s) {
        Shift unwantedShift = s.getUnwantedShift();
        Shift wantedShift = s.getWantedShift();
        Shifter requestingShifter = unwantedShift.getShifter();
        Shifter respondingShifter = wantedShift.getShifter();
        unwantedShift.setShifter(respondingShifter);
        wantedShift.setShifter(requestingShifter);
    }

    /**
     * Shortens a date format
     *
     * @param date
     * @return shortened date
     */
    public String dateFormatterStd(String date) {
        SimpleDateFormat inputGiven = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", new Locale("en_GB"));
        SimpleDateFormat output = new SimpleDateFormat("EEE dd MMM'\n'HH:mm", new Locale("en_GB"));
        Date given = null;
        try {
            given = inputGiven.parse(date);
            return output.format(given);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * Changes a date to a specified format
     *
     * @param date
     * @param format
     * @return changed date
     */
    public String dateFormatterCon(String date, String format) {
        SimpleDateFormat inputGiven = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", new Locale("en_GB"));
        SimpleDateFormat output = new SimpleDateFormat(format);
        Date given = null;
        try {
            given = inputGiven.parse(date);
            return output.format(given);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}

