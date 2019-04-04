package com.assignment.alt_shift_cs991.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Required data access object interface which enables us top query the SQLite database.
 */
@Dao
public interface DaoAccess {

    /**
     * Adds a shifter to the database.
     *
     * @param shifter
     */
    @Insert
    void insertShifter(Shifter shifter);

    /**
     * Adds a list of shifters to the database.
     *
     * @param shifters
     */
    @Insert
    void insertMultipleShifters(List<Shifter> shifters);

    /**
     * Retrieves a shifter from the database using an SQL query.
     *
     * @param userID
     * @param password
     * @return
     */
    @Query("SELECT * FROM Shifter WHERE userID = :userID and password = :password")
    Shifter getShifter(String userID, String password);

    /**
     * Returns all the shifters in the database using an SQL query.
     *
     * @return
     */
    @Query("SELECT * FROM Shifter")
    List<Shifter> getAllShifters();

    /**
     * Returns all userIDs of shifters in database using an SQL query
     *
     * @return
     */
    @Query("SELECT userID FROM Shifter")
    List<String> getAllShiftersUserID();

    /**
     * Updates a shifter record within the database.
     *
     * @param shifter
     */
    @Update
    void updateShifter(Shifter shifter);

    /**
     * Removes a shifter record from the database.
     *
     * @param shifter
     */
    @Delete
    void deleteShifter(Shifter shifter);

}
