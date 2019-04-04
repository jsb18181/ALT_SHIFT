package com.assignment.alt_shift_cs991.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A ShiftSwap is a pair of shifts proposed to swap the dates.
 */
public class ShiftSwap implements Parcelable {

    private Shift shift1;
    private Shift shift2;
    private int status;

    /**
     * Constructor for a ShiftSwap.
     *
     * @param s1 unwanted shift
     * @param s2 wanted shift
     */
    public ShiftSwap(Shift s1, Shift s2) {
        shift1 = s1;
        shift2 = s2;
        status = 0;
    }

    /**
     * Getter for the unwanted shift.
     *
     * @return unwanted shift
     */
    public Shift getUnwantedShift() {
        return shift1;
    }

    /**
     * Getter for the wanted shift.
     *
     * @return wanted shift
     */
    public Shift getWantedShift() {
        return shift2;
    }

    /**
     * Setter for the unwanted shift.
     *
     * @param s unwanted shift
     */
    public void setUnwantedShift(Shift s) {
        shift1 = s;
    }

    /**
     * Setter for the wanted shift.
     *
     * @param s wanted shift
     */
    public void setWantedShift(Shift s) {
        shift2 = s;
    }

    /**
     * Getter for the swap status.
     *
     * @return swap status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Setter for the swap status if status value is between -1 and 1.
     *
     * @param status swap status
     */
    public void setStatus(int status) {
        if (status >= -1 && status <= 1) {
            this.status = status;
        }
    }

    /**
     * Sets the status to pending.
     */
    public void setStatusPending() {
        this.status = 0;
    }

    /**
     * Sets the status to accepted.
     */
    public void setStatusAccepted() {
        this.status = 1;
    }

    /**
     * Sets the status to rejected.
     */
    public void setStatusRejected() {
        this.status = -1;
    }

    /**
     * Checks if a shiftSwap with specific dates has already been created.
     *
     * @param s shiftSwap
     * @return boolean
     */
    public boolean equals(ShiftSwap s) {
        if (s.getUnwantedShift() == this.getUnwantedShift() && s.getWantedShift() == this.getWantedShift()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reads information for enabling the class to be Parcelable.
     *
     * @param in
     */
    protected ShiftSwap(Parcel in) {
        status = in.readInt();
        this.shift1 = in.readParcelable(Shift.class.getClassLoader());
        this.shift2 = in.readParcelable(Shift.class.getClassLoader());

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeParcelable(this.shift1, flags);
        dest.writeParcelable(this.shift2, flags);
    }

    public static final Creator<ShiftSwap> CREATOR = new Creator<ShiftSwap>() {
        @Override
        public ShiftSwap createFromParcel(Parcel in) {
            return new ShiftSwap(in);
        }

        @Override
        public ShiftSwap[] newArray(int size) {
            return new ShiftSwap[size];
        }
    };

}
