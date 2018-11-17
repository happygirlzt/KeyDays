/**
 * Created by happygirlzt
 *
 * Nov 2018
 */

package com.happygirlzt.keydays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TestActivity extends AppCompatActivity {

    TextView name;
    Button logout;
    Button toPro;

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        name = findViewById(R.id.testtextView);
        logout = findViewById(R.id.testlogout);
        toPro = findViewById(R.id.toProfile);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        assert user != null;
        name.setText(user.getEmail());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent = new Intent(TestActivity.this, LoginActivity.class);

                // clear the previous activities
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            }
        });

        toPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this, ProfileActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = auth.getCurrentUser();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
