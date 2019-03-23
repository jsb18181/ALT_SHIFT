package com.assignment.alt_shift_cs991.model;

import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.room.Room;

public class Application extends android.app.Application {
    /**
     * Backend data storage and link to database
     */
    public ShiftManager shiftManager;
    public CalendarManager calendarManager;
    public static final String LI_NAME = "shifterDetails";
    SharedPreferences localData;
    public Database db;
    public String dateClicked;
    public ShiftSwap selectedCurrentShiftSwap;

    /**
     * Initialises the Application
     */
    @Override
    public void onCreate() {

        super.onCreate();
        shiftManager = new ShiftManager();
        calendarManager = new CalendarManager();
        localData = getSharedPreferences(LI_NAME, 0);
        db = Room.databaseBuilder(getApplicationContext(), Database.class, "login-db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = new Date(calendar.getTimeInMillis());
        dateClicked = date.toString();
        fillTheModel();
    }

    /**
     * Setter for dateClicked
     *
     * @param dateClicked
     */
    public void setDateClicked(String dateClicked) {
        this.dateClicked = dateClicked;
    }

    /**
     * Getter for dateClicked
     *
     * @return dateClicked
     */
    public String getDateClicked() {
        return dateClicked;
    }


    public void fillTheModel() {
        List<Shifter> dbShifters = db.daoAccess().getAllShifters();
        for (Shifter s : dbShifters) {
            shiftManager.addShifter(s);
        }
    }

    /**
     * Stores data of the logged in user
     *
     * @param shifter logged in used
     */
    public void storedLoggedInUser(Shifter shifter) {

        SharedPreferences.Editor spEditor = localData.edit();
        spEditor.putString("name", shifter.getFirstName());
        spEditor.putString("surname", shifter.getSurname());
        spEditor.putString("password", shifter.getPassword());
        spEditor.putString("username", shifter.getUserID());
        spEditor.commit();
    }

    /**
     * Getter for the logged in user
     *
     * @return logged in Shifter
     */
    public Shifter getLoggedInShifter() {
        String name = localData.getString("name", "");
        String surname = localData.getString("surname", "");
        String password = localData.getString("password", "");
        String username = localData.getString("username", "");
        Shifter storedShifter = shiftManager.getShifter(username, password);
        return storedShifter;
    }

    /**
     * Setter for the logged in user
     *
     * @param loggedIn
     */
    public void setUserLoggedIn(Boolean loggedIn) {
        SharedPreferences.Editor spEditor = localData.edit();
        spEditor.putBoolean("LoggedIn", loggedIn);
        spEditor.commit();
    }

    /**
     * Clears the data of the logged in user
     */
    public void clearUserData() {
        SharedPreferences.Editor spEditor = localData.edit();
        spEditor.clear();
        spEditor.commit();
    }

    /**
     * Removes a shiftSwap from the list of shiftSwaps
     *
     * @param s
     */
    public void removeSwap(ShiftSwap s) {
        shiftManager.removeSwap(s);
    }

}
