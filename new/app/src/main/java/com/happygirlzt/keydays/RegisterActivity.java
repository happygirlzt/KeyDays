package com.happygirlzt.keydays;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail, etName, etPassword, etConfirmPassword;
    Button registerButton;
    FirebaseAuth firebaseAuth;

    final static FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.et_email);
        etName = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        registerButton = findViewById(R.id.register_button);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            Toast.makeText(getApplicationContext(), "You are logged in", Toast.LENGTH_SHORT).show();

            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();
                final String name = etName.getText().toString();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Please enter your email", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Please enter password", Toast.LENGTH_SHORT).show();
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(),"Password must be at least 6 characters",Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(getApplicationContext(),"Please enter password again", Toast.LENGTH_SHORT).show();
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Please confirm your password again!", Toast.LENGTH_SHORT).show();
                }


                // Created user
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = firebaseAuth.getCurrentUser();

                                    // writeNewUser(firebaseAuth.getCurrentUser().getUid(), name, email);
                                    String key = database.getReference("users").push().getKey();

                                    User u = new User();
                                    u.setUserid(user.getUid());
                                    u.setUsername(name);
                                    u.setUseremail(email);


                                    Map<String, Object> childUpdates = new HashMap<>();
                                    assert key != null;
                                    childUpdates.put(key, u.toFirebaseObj());

                                    Toast.makeText(getApplicationContext(), "Register successful!", Toast.LENGTH_SHORT).show();
                                    // Registered -> Login
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(),"E-mail or password is wrong",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}
