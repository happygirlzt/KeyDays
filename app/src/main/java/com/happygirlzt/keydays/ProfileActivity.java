package com.happygirlzt.keydays;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference database;

    ListView listView;
    ArrayList<DateItem> dateList = new ArrayList<>();
    DateAdaper myAdapter;

    TextView loggedInUser;
    TextView loggedInEmail;

    final static public String TAG = "Start LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        loggedInUser = findViewById(R.id.loggedInUser);
        loggedInEmail = findViewById(R.id.loggedInEmail);
        listView = findViewById(R.id.listview);

        myAdapter = new DateAdaper(this, dateList);
        listView.setAdapter(myAdapter);

        //Intent intent = getIntent();
        //loggedInUser.setText(intent.getStringExtra("User_name"));
        //loggedInEmail.setText(intent.getStringExtra("User_email"));

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user == null) {
                    Toast.makeText(ProfileActivity.this, "You should log in first!", Toast.LENGTH_SHORT).show();
                    // finish the current one
                    finish();
                    startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                }

                startActivity(new Intent(ProfileActivity.this, AddDateActivity.class));
            }
        });

        String queryUser = "dates/" + user.getUid();
        database = FirebaseDatabase.getInstance().getReference(queryUser);
        database.addListenerForSingleValueEvent(valueEventListener);



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.loggedInUser);
        if (user == null) {
            navUsername.setText("Please Log in!");
        } else {
            String regards = "Welcome, back: ";
            navUsername.setText(regards);
        }

        TextView navUsermail = headerView.findViewById(R.id.loggedInEmail);
        if (user != null) {
            String logEmail = user.getEmail();
            navUsermail.setText(logEmail);
        }
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Handle the already logged in user
        if (auth.getCurrentUser() != null) {

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // Create the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    // action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_log_out) {
            if (auth.getCurrentUser() == null) {
                Toast.makeText(this, "You haven't logged in!", Toast.LENGTH_SHORT).show();
            } else {
                auth.signOut();

                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);

                // clear the previous activities
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                startActivity(intent);

                finish();
            }

        } else if (id == R.id.nav_dates) {
            Toast.makeText(ProfileActivity.this, "You taped the dates", Toast.LENGTH_LONG).show();
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            dateList.clear();

            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DateItem dateItem = snapshot.getValue(DateItem.class);
                    dateList.add(dateItem);
                }

                myAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
