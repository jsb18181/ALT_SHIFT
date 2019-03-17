package com.assignment.alt_shift_cs991;

import android.app.Application;
import android.content.SharedPreferences;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AltShift_Application extends Application {

    public ShiftManager shiftManager;
    public CalendarManager calendarManager;
    public static final String LI_NAME = "shifterDetails";
    SharedPreferences localData;

    @Override
    public void onCreate(){

        super.onCreate();
        shiftManager = new ShiftManager();
        calendarManager = new CalendarManager();
        fillTheModel();
        localData = getSharedPreferences(LI_NAME, 0);
    }
    public void fillTheModel() {

        Shifter one = new Shifter("jsb18181", "qwerty", "James", "Mackenzie");
        Shifter two = new Shifter("5678", "qwerty1", "Anne", "Two");
        Shifter three = new Shifter("9012", "qwerty2", "Mike", "Three");
        Shifter four = new Shifter("3456", "qwerty3", "Laura", "Four");
        Shifter five = new Shifter("7890", "qwerty4", "Sam", "Five");
        Shifter six = new Shifter("1212", "qwerty5", "Oscar", "Six");
        Shifter seven = new Shifter("3234", "qwerty6", "Ryan", "Seven");
        Shifter eight = new Shifter("4345", "qwerty7", "Tina", "Eight");
        Shifter nine = new Shifter("5456", "qwerty8", "Tom", "Nine");
        Shifter ten = new Shifter("6565", "qwerty9", "George", "Ten");
        Shifter eleven = new Shifter("6565", "qwerty9", "George", "Eleven");

        shiftManager.addShifter(one);
        shiftManager.addShifter(two);
        shiftManager.addShifter(three);
        shiftManager.addShifter(four);
        shiftManager.addShifter(five);
        shiftManager.addShifter(six);
        shiftManager.addShifter(seven);
        shiftManager.addShifter(eight);
        shiftManager.addShifter(nine);
        shiftManager.addShifter(ten);
        shiftManager.addShifter(eleven);


        Shift shift1 = new Shift("Thu Mar 21 09:00:00 GMT 2019", two);
        Shift shift2 = new Shift("Fri Mar 22 09:00:00 GMT 2019", two);
        Shift shift3 = new Shift("Sat Mar 23 09:00:00 GMT 2019", two);
        Shift shift4 = new Shift("Sun Mar 24 09:00:00 GMT 2019", four);
        Shift shift5 = new Shift("Mon Mar 25 09:00:00 GMT 2019", five);
        Shift shift6 = new Shift("Tue Mar 26 09:00:00 GMT 2019", six);
        Shift shift7 = new Shift("Thu Mar 21 09:00:00 GMT 2019", seven);
        Shift shift8 = new Shift("Fri Mar 22 09:00:00 GMT 2019", eight);
        Shift shift9 = new Shift("Sat Mar 23 09:00:00 GMT 2019", nine);
        Shift shift10 = new Shift("Sat Mar 23 09:00:00 GMT 2019", ten);

        Shift shift11 = new Shift("Sat Mar 23 09:00:00 GMT 2019", two);

        shiftManager.addShift(shift1);
        shiftManager.addShift(shift2);
        shiftManager.addShift(shift3);
        shiftManager.addShift(shift4);
        shiftManager.addShift(shift5);
        shiftManager.addShift(shift6);
        shiftManager.addShift(shift7);
        shiftManager.addShift(shift8);
        shiftManager.addShift(shift9);
        shiftManager.addShift(shift10);
    }

    public void storedLoggedInUser(Shifter shifter){

        SharedPreferences.Editor spEditor = localData.edit();
        spEditor.putString("name", shifter.getFirstName());
        spEditor.putString("surname", shifter.getSurname());
        spEditor.putString("password", shifter.getPassword());
        spEditor.putString("username", shifter.getUserID());
        spEditor.commit();
    }

    public Shifter getLoggedInShifter(){


        String name = localData.getString("name", "");
        String surname = localData.getString("surname", "");
        String password = localData.getString("password", "");
        String username = localData.getString("username", "");
        Shifter storedShifter = shiftManager.getShifter(username, password);

        return storedShifter;
    }

    public void setUserLoggedIn(Boolean loggedIn){

        SharedPreferences.Editor spEditor = localData.edit();
        spEditor.putBoolean("LoggedIn", loggedIn);
        spEditor.commit();

    }
    public void clearUserData(){
        SharedPreferences.Editor spEditor = localData.edit();
        spEditor.clear();
        spEditor.commit();
    }


}
