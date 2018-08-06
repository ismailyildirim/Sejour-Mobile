package com.example.ismai.projectbase.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.ismai.projectbase.R;


public class Main2Activity extends AppCompatActivity {
    Button button, btn_toggle;
    static ProgressDialog loadingBar;
    CheckBox checkBox;
    TabHost tabHost;
    TabHost.TabSpec page1, page2;
    RecyclerView.LayoutManager layoutManager, layoutManager2;
    Context context = Main2Activity.this;
    RecyclerView recyclerViewTour, recyclerViewHotel;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setProgressDialog();
        tanimla();
        setTabHost();
        final String gelen = getIntent().getStringExtra("logid");
        final int logid = Integer.parseInt(gelen);
        UtilsRetrofit.getOurInstance().istekHotel(logid, recyclerViewHotel, context);
        UtilsRetrofit.getOurInstance().istekTour(logid, recyclerViewTour, context);

    }

    public void tanimla() {
        checkBox = findViewById(R.id.checkBox);
        recyclerViewTour = findViewById(R.id.recylerView);
        recyclerViewHotel = findViewById(R.id.recylerView2);
        layoutManager = new LinearLayoutManager(Main2Activity.this);
        layoutManager2 = new LinearLayoutManager(Main2Activity.this);

        recyclerViewTour.setLayoutManager(layoutManager);
        recyclerViewHotel.setLayoutManager(layoutManager2);
        tabHost = findViewById(R.id.tabHost);

        button = findViewById(R.id.button);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if ("s1".equals(tabId)) {
                    setTabColor(tabHost);
                }
                if ("s2".equals(tabId)) {
                    setTabColor(tabHost);
                }
            }
        });
        drawerLayout = findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        btn_toggle = findViewById(R.id.button_toggle);
        btn_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case (R.id.nav_logout):
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);


                }
                return true;
            }
        });
        View linearLayout = navigationView.inflateHeaderView(R.layout.navigation_header);
        TextView name = linearLayout.findViewById(R.id.userName);

        name.setText(getIntent().getStringExtra("username"));

    }

    public void onClickTabHost(View view) {
        if (tabHost.getCurrentTabTag().equals("s1")) {
            tabHost.setCurrentTabByTag("s2");
            tabHost.getTabWidget().getChildAt(1).setBackground(getResources().getDrawable(R.drawable.defaultbutton));


        } else {

            tabHost.setCurrentTabByTag("s1");
            tabHost.getTabWidget().getChildAt(0).setBackground(getResources().getDrawable(R.drawable.defaultbutton));


        }
    }

    public void setTabHost() {
        tabHost.setup();
        page1 = tabHost.newTabSpec("s1");
        page1.setContent(R.id.tab1);
        page1.setIndicator("Hotel");
        tabHost.addTab(page1);
        tabHost.setCurrentTab(0);

        page2 = tabHost.newTabSpec("s2");
        page2.setContent(R.id.tab2);
        page2.setIndicator("Tour");
        tabHost.addTab(page2);
        setTabColor(tabHost);
    }

    public void setTabColor(TabHost tabhost) {

        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {

            tabhost.getTabWidget().getChildAt(i).setBackground(getResources().getDrawable(R.drawable.tabhost)); //unselected
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#ffffff"));

        }
        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackground(getResources().getDrawable(R.drawable.defaultbutton)); // selected

    }

    public void setProgressDialog() {
        loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Hosgeldiniz");
        loadingBar.setMessage("Oteller yuklenirken lutfen bekleyin...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
    }

}
