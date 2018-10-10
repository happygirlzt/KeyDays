package happygirlzt.com.keydays.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import happygirlzt.com.keydays.R;
import happygirlzt.com.keydays.adapters.DateRecyclerViewAdapter;

import happygirlzt.com.keydays.models.KeyDate;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DateRecyclerViewAdapter adapter;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 加载R.layout.activity_main的布局
        setContentView(R.layout.activity_main);

        // 初始化工具栏，让toolbar向actionbar一样工作
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("DrawerLayout测试");
        setSupportActionBar(toolbar);

        // 初始化抽屉样式
        drawerLayout = findViewById(R.id.drawer_layout);
        // 初始化navigation
        navigationView = findViewById(R.id.nav_view);

        // 绑定drawerLayout 和 navigationView
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 菜单栏的点击监听
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_past_days:

                        break;
                        default:
                            break;
                }
                return true;
            }
        });

        displayView(R.id.nav_upcoming);



        // RecyclerView mRecyclerView = findViewById(R.id.rvDates);
        // 设置线性layout
        // mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // 添加默认动画
        // mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        // adapter = new DateRecyclerViewAdapter(keydates);

        // 把adapter添加到RecyclerView上
        // mRecyclerView.setAdapter(adapter);
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

    // inflate the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 把tool_bar当成菜单栏
        getMenuInflater().inflate(R.menu.tool_bar, menu);
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
}
