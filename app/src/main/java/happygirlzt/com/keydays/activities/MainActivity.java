package happygirlzt.com.keydays.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import happygirlzt.com.keydays.R;
import happygirlzt.com.keydays.databases.DatabaseHelper;
import happygirlzt.com.keydays.models.KeyDate;

// import happygirlzt.com.keydays.adapters.DateRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // private DateRecyclerViewAdapter adapter;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private List<KeyDate> keyDateList;
    private DatabaseHelper databaseHelper;

    Context context = MainActivity.this;

    // list of countries
    private static final String[] WORDS = {
            "Afghanistan",
            "Albania",
            "Algeria",
            "Andorra",
            "Angola",
            "Argentina",
            "Armenia",
            "Australia",
            "Austria",
            "Azerbaijan",
            "Bahamas",
            "Bahrain",
            "Bangladesh",
            "Barbados",
            "Belarus",
            "Belgium",
            "Belize",
            "Benin",
            "Bhutan",
            "Bolivia",
            "Bosnia Herzegovina",
            "Botswana",
            "Brazil",
            "Brunei",
            "Bulgaria",
            "Burkina",
            "Burundi",
            "Cambodia",
            "Cameroon"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 加载R.layout.activity_main的布局
        setContentView(R.layout.app_bar_main);

        // 初始化工具栏，让t向actionbar一样工作
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*recyclerView = findViewById(R.id.rvDates);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // 绑定drawerLayout 和 navigationView
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
*/

        /*fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddDateActivity.this, AddDateActivity.class));
            }
        });*/

        /*keyDateList = new ArrayList<>();
        adapter = new DateRecyclerViewAdapter(keyDateList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        databaseHelper = new DatabaseHelper(activity);*/

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < WORDS.length; i++) {
            list.add(WORDS[i]);
        }

        // have to make a adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.item_date,
                list);

        ListView listView = findViewById(R.id.word_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*@Override
                        String definition = WORDS[position * 2 + 1];

                        public void onItemClick(AdapterView<> parent, View view, int position, long id) {
                    Log.d("list", "the user clicked item " + position);

                    TextView definView = (TextView) findViewById()
                }*/
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }


    // 设置navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displayView(item.getItemId());
        return true;
    }

    public void displayView(int viewId) {

    }

    public void fabAddDate(View view) {
        // go to the AddDateActivity
Intent intent = new Intent(this, AddDateActivity.class);
startActivity(intent);
    }
}
