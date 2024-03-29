package com.assignment.alt_shift_cs991.activities;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.adapters.CalendarManager;
import com.assignment.alt_shift_cs991.adapters.ManagerAdapter;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Calendar activity for Manager users.
 */
public class ManagerCalendarActivity extends CalendarActivity {

    public CompactCalendarView calendarView;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private CalendarManager calendarManager = new CalendarManager();
    public RecyclerView recyclerView;
    private ManagerAdapter shifterAdapter;
    protected Application model;
    private Boolean isShowing;

    /**
     * Initialises activity with all shifts.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_calendar_view);
        initToolbar();

        model = (Application) getApplication();
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        final ActionBar actionBar = getSupportActionBar();
        calendarView = findViewById(R.id.compactcalendar_view);
        calendarView.setUseThreeLetterAbbreviation(true);
        calendarManager.shiftPopulate(calendarView, model.shiftManager.getAllShiftsDates());
        actionBar.setTitle(dateFormat.format(new Date()));
        shifterAdapter = new ManagerAdapter(model.shiftManager.getAllShiftsByDate(model.getDateClicked()));
        recyclerView = findViewById(R.id.shifter_shifts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(shifterAdapter);
        isShowing = true;

        Callback callback = new Callback() {
            @Override
            public void finishActivity() {
                finish();
            }

            @Override
            public Application getModel() {
                return model;
            }

            @Override
            public CompactCalendarView getCalendar() {
                return calendarView;
            }

            @Override
            public CalendarManager getCalendarManager() {
                return calendarManager;
            }

        };
        shifterAdapter.setCallBack(callback);

        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            /**
             * Shows all shifts on clicked date.
             * @param dateClicked
             */
            @Override
            public void onDayClick(Date dateClicked) {
                model.setDateClicked(dateClicked.toString());
                shifterAdapter.setItems(model.shiftManager.getAllShiftsByDate(dateClicked.toString()));
            }

            /**
             * Changes month on the calendar when scrolled.
             * @param firstDayOfNewMonth
             */
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormat.format(firstDayOfNewMonth));
            }
        });
        fab.setOnClickListener(v -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", new Locale("en_GB"));
            Date dateClicked = new Date();
            Date today = Calendar.getInstance().getTime();
            try {
                dateClicked = dateFormat.parse(model.getDateClicked());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (dateClicked.after(today)) {
                Intent intent = new Intent(getApplicationContext(), ShiftAddingActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "You cannot assign a shift to a date that has passed.", Toast.LENGTH_LONG).show();
            }
        });

        Button hideShow = findViewById(R.id.hideShowCal);
        hideShow.setOnClickListener(v -> {
            if (isShowing) {
                calendarView.hideCalendar();
                isShowing = false;
                hideShow.setText(R.string.Show_Calender);
            } else {
                calendarView.showCalendar();
                isShowing = true;
                hideShow.setText(R.string.Hide_Calender);
            }
        });
    }

    /**
     * Populates the calendar with shifts as events on dates.
     */
    @Override
    protected void onResume() {
        super.onResume();
        calendarView.removeAllEvents();
        calendarManager.shiftPopulate(calendarView, model.shiftManager.getAllShiftsDates());
        shifterAdapter.setItems(model.shiftManager.getAllShiftsByDate(model.getDateClicked()));
    }

    /**
     * Callback interface which enables us to use these methods within the ManagerAdapter, where
     * it is necessary to use some of the methods from our Application class.
     */
    public interface Callback {

        void finishActivity();

        Application getModel();

        CompactCalendarView getCalendar();

        CalendarManager getCalendarManager();
    }
}
