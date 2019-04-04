package com.assignment.alt_shift_cs991.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.model.Shifter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Activity that creates a toolbar.
 */
public class ToolbarActivity extends AppCompatActivity {

    private TextView textSwapItemCount;
    protected Application model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_activity);
        initToolbar();
        model = (Application) getApplication();
    }

    /**
     * Method to initialise the toolbar within various activities.
     */
    public void initToolbar() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    /**
     * Initialises the toolbar buttons and enables the user to click the actionView button.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_more);

        final MenuItem menuItem1 = menu.findItem(R.id.action_logout);

        View actionView1 = menuItem1.getActionView();

        View actionView = menuItem.getActionView();
        textSwapItemCount = actionView.findViewById(R.id.swap_badge);

        setupBadge(model.shiftManager.getCountAvailableSwaps(model.getLoggedInShifter()));

        actionView.setOnClickListener(v -> {
            onOptionsItemSelected(menuItem);
        });
        actionView1.setOnClickListener(v -> {
            onOptionsItemSelected(menuItem1);
        });
        return true;
    }

    /**
     * Determines where to send the user depending on the button clicked.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Shifter shifter = model.getLoggedInShifter();
        if (id == R.id.action_home && !shifter.isManager()) {
            Intent intent = new Intent(this, CalendarActivity.class);
            startActivity(intent);
            return false;
        }
        if (id == R.id.action_home && shifter.isManager()) {
            Intent intent = new Intent(this, ManagerCalendarActivity.class);
            startActivity(intent);
            return false;
        } else if (id == R.id.action_logout) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return false;
        } else if (id == R.id.action_more) {
            Intent intent = new Intent(ToolbarActivity.this, PendingSwapsEmp.class);
            startActivity(intent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Sets up the icon which shows the user how many pending swap they currently have.
     *
     * @param mItemCount
     */
    private void setupBadge(int mItemCount) {
        if (textSwapItemCount != null) {
            if (mItemCount == 0) {
                if (textSwapItemCount.getVisibility() != View.GONE) {
                    textSwapItemCount.setVisibility(View.GONE);
                }
            } else {
                textSwapItemCount.setText(String.valueOf(Math.min(mItemCount, 99)));
                if (textSwapItemCount.getVisibility() != View.VISIBLE) {
                    textSwapItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * Refreshes the badge information.
     */
    @Override
    protected void onResume() {
        super.onResume();
        setupBadge(model.shiftManager.getCountAvailableSwaps(model.getLoggedInShifter()));
    }
}



