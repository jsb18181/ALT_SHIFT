package com.assignment.alt_shift_cs991.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A Shift is a date and a person (shifter) working on that date.
 */
public class Shift implements Parcelable {

    private String date;
    private Shifter shifter;
    private String name;
    private String surname;
    private String userName;
    private String password;
    private String swapDate;
    private String swapUserName;
    private String swapPassword;

    /**
     * Constructor for a Shift
     *
     * @param date    Date of Shift
     * @param shifter Employee on Shift
     */
    public Shift(String date, Shifter shifter) {
        this.date = date;
        this.shifter = shifter;
        name = shifter.getFirstName();
        surname = shifter.getSurname();
        userName = shifter.getUserID();
        password = shifter.getPassword();
        swapUserName = "";
        swapPassword = "";
        shifter.getMyShifts().add(this);
        swapDate = "";
    }

    /**
     * Getter for Date
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter for swapDate
     *
     * @return swapDate
     */
    public String getSwapDate() {
        return swapDate;
    }

    /**
     * Getter for swapUserName
     *
     * @return swapUserName
     */
    public String getSwapUserName() {
        return swapUserName;
    }

    /**
     * Getter for swapPassword
     *
     * @return swapPassword
     */
    public String getSwapPassword() {
        return swapPassword;
    }

    /**
     * Getter for Shifter
     *
     * @return shifter
     */
    public Shifter getShifter() {
        return shifter;
    }

    /**
     * Getter for name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for surname
     *
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Getter for userName
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Getter for password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for swapDate
     *
     * @param swapDate
     */
    public void setSwapDate(String swapDate) {
        this.swapDate = swapDate;
    }

    /**
     * Setter for setSwapUserName
     *
     * @param swapUserName
     */
    public void setSwapUserName(String swapUserName) {
        this.swapUserName = swapUserName;
    }

    /**
     * Setter for swapPassword
     *
     * @param swapPassword
     */
    public void setSwapPassword(String swapPassword) {
        this.swapPassword = swapPassword;
    }

    /**
     * Setter for name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for Date
     *
     * @param date date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Setter for Shifter
     *
     * @param shifter shifter
     */
    public void setShifter(Shifter shifter) {
        this.shifter = shifter;
    }

    protected Shift(Parcel in) {
        date = in.readString();
        name = in.readString();
        surname = in.readString();
        swapDate = in.readString();
        userName = in.readString();
        password = in.readString();
        swapUserName = in.readString();
        swapPassword = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(swapDate);
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeString(swapUserName);
        dest.writeString(swapPassword);
    }

    public static final Creator<Shift> CREATOR = new Creator<Shift>() {
        @Override
        public Shift createFromParcel(Parcel in) {
            return new Shift(in);
        }

        @Override
        public Shift[] newArray(int size) {
            return new Shift[size];
        }
    };

}
