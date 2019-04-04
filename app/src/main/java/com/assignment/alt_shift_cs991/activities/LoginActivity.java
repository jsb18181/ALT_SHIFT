package com.assignment.alt_shift_cs991.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.model.Shifter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

/**
 * Login Activity for users.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText userName, password;


    private CardView loginButton;
    private int passwordCount;
    protected Application model;

    /**
     * Initialises activity with empty editTexts for user input.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        model = (Application) getApplication();
        userName = findViewById(R.id.editText3);
        password = findViewById(R.id.editText4);
        loginButton = findViewById(R.id.cardButton);


        loginButton.setOnClickListener(v -> {
            model.clearUserData();
            model.setUserLoggedIn(false);
            Shifter shifter = model.db.daoAccess().getShifter(userName.getText().toString(), password.getText().toString());
            if (shifter != null) {
                model.setUserLoggedIn(true);
                model.storedLoggedInUser(shifter);
                if (shifter.isManager()) {
                    Intent intent = new Intent(getApplicationContext(), ManagerCalendarActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Hello " + shifter.getFirstName() + "!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                    intent.putExtra("SHIFTER1", shifter);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Hello " + shifter.getFirstName() + "!", Toast.LENGTH_SHORT).show();
                }
            } else {

                Toast.makeText(getApplicationContext(), "Wrong Username or Password, please try again", Toast.LENGTH_SHORT).show();

                passwordCount++;
                if (passwordCount > 2) {
                    loginButton.setEnabled(false);
                    Toast.makeText(getApplicationContext(), "Attempt limit reached, please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Takes user to the register activity when button is clicked.
     *
     * @param v
     */
    public void register(View v) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

}
