package happygirlzt.com.keydays.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import happygirlzt.com.keydays.R;
import happygirlzt.com.keydays.databases.DatabaseHelper;
import happygirlzt.com.keydays.models.User;

public class RegisterActivity extends AppCompatActivity {

    private Button btn;

    private EditText etEmail;
    private EditText etPassword;
    private EditText etPassword1;
    private EditText etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    // 初始化view
    private void initView() {
        btn = findViewById(R.id.buttonCreateAccount);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        etPassword1 = findViewById(R.id.editTextConfirmPassword);
        etUsername = findViewById(R.id.editTextUserName);
    }

    // Register button的点击事件: add a new user
    public void createNewAccount(View view) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        String email = String.valueOf(etEmail.getText());
        String password = String.valueOf(etPassword.getText());
        String password1 = String.valueOf(etPassword1.getText());
        String username = String.valueOf(etUsername.getText());

        while (!password1.equals(password)) {
            Toast.makeText(RegisterActivity.this, "Password not match!", Toast.LENGTH_LONG).show();
            password1 = String.valueOf(etPassword1.getText());
        }

        User user = new User(username, password, email);

        dbHelper.addUser(user);

        Toast.makeText(RegisterActivity.this, "Successful Register!", Toast.LENGTH_SHORT).show();
    }
}
