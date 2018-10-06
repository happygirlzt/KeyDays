package happygirlzt.com.keydays.activities;

import happygirlzt.com.keydays.models.User;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.View;


import happygirlzt.com.keydays.R;
import happygirlzt.com.keydays.databases.DatabaseHelper;

import static android.view.View.*;
import android.content.SharedPreferences;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // TextInputLayout
    private final AppCompatActivity activity = LoginActivity.this;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    // EditTexts
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    // Login Button
    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;

    // DatabaseHelper
    private DatabaseHelper databaseHelper;

    // Remember the user is logged in
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        initViews();
        appCompatButtonLogin.setOnClickListener(onclick);
    }

    OnClickListener onclick = new OnClickListener() {
        @Override
        public void onClick(View v) {

            String Email = textInputEditTextEmail.getText().toString();
            String Password = textInputEditTextPassword.getText().toString();

            if (isEmailValid(Email) && isPasswordValid(Password)) {
                // Authenticate user
                boolean currentUserExists = databaseHelper.authenticate(new User(null,
                        null, Email, Password));

                // Check if authentication is successful
                if (currentUserExists) {
                    goToMainActivity();
                    sp.edit().putBoolean("logged",true).apply();

                    Snackbar.make(appCompatButtonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(appCompatButtonLogin, "Failed to log in", Snackbar.LENGTH_LONG).show();
                }
            }
        }
    };

    private boolean isEmailValid(String email) {

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textInputLayoutEmail.setError("Please Enter valid email");
            return false;
        } else {
            textInputLayoutEmail.setError(null);
            return true;
        }
    }

    private boolean isPasswordValid(String password) {

        if (password.isEmpty()) {
            textInputLayoutPassword.setError("Please Enter valid password!");
            return false;
        } else {
            if (password.length() > 4) {
                return true;
            } else {
                textInputLayoutPassword.setError("Password is too short");
                return false;
            }
        }
    }

    // connet XML views to its objects
    private void initViews() {
            textInputEditTextEmail = findViewById(R.id.editTextEmail);
            textInputEditTextPassword = findViewById(R.id.editTextPassword);
            textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
            textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
            appCompatButtonLogin = findViewById(R.id.email_sign_in_button);
    }

    // Keep the user logged in
    public void goToMainActivity() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
