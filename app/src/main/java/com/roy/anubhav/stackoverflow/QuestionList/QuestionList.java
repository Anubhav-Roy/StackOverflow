package com.roy.anubhav.stackoverflow.QuestionList;

import android.os.Bundle;



import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.roy.anubhav.stackoverflow.ApplicationClass;
import com.roy.anubhav.stackoverflow.R;
import com.roy.anubhav.stackoverflow.utils.AppPreferences;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;

public class QuestionList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Setting up variables (tags along with their menu ids ) to be used in the NavigationView
    private String firstTag="";
    private static final int MENU_ITEM_ITEM1 = 1;
    private String secondTag = "";
    private static final int MENU_ITEM_ITEM2 = 2;
    private String thirdTag = "";
    private static final int MENU_ITEM_ITEM3 = 3;
    private String fourthTag = "";
    private static final int MENU_ITEM_ITEM4 = 4;

    //Retrieving appPreferences from the Application class
    private AppPreferences appPreferences = (((ApplicationClass) ApplicationClass.getContext())).getAppPreferences();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        //Setting up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");     //Removing the title

        //NavigationView and drawer setup
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //Custom Initializations
        setupNavView(navigationView);
        setupPager(appPreferences.sharedPrefs.getString("tagOne",null));
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

    /**
     * Sets up the pagerView for the specific tag ( e.g Java , C# )
     * Called whenever the user clicks on a tag in the NavView
     * @param tag   the tag user has selected
     */
    private void setupPager(String tag){

        TabLayout tabLayout =  findViewById(R.id.tab_layout);
        tabLayout.removeAllTabs();      //Removing the previous tabs

        tabLayout.addTab(tabLayout.newTab().setText("Your #"));
        tabLayout.addTab(tabLayout.newTab().setText("Hot"));
        tabLayout.addTab(tabLayout.newTab().setText("Weekly"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);

        //Setting up the pageAdatper as well as setting the variable tag in the adapter which
        //the pageAdapter can then further pass to the fragments to load the appropriate tagged questions
        final PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.tag = tag;
        viewPager.setAdapter(pageAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * We need to setup the NavView manually since the items in the NavView have to be set dynamically ,
     * i.e. , we aren't populating the NavVIew menu from a xml. We set the navView to  a new menu which we
     * create programmatically to be the tags the user has selected , which we get from the sharedPrefs
     * @param navigationView    The NavView we are setting up
     */
    public void setupNavView(NavigationView navigationView){


        Menu menu = navigationView.getMenu();
        menu.clear();

        firstTag = appPreferences.sharedPrefs.getString("tagOne",null);
        secondTag = appPreferences.sharedPrefs.getString("tagTwo",null);
        thirdTag = appPreferences.sharedPrefs.getString("tagThree",null);
        fourthTag = appPreferences.sharedPrefs.getString("tagFour",null);


        menu.add(Menu.NONE,MENU_ITEM_ITEM1,Menu.NONE,"# "+firstTag);
        menu.add(Menu.NONE,MENU_ITEM_ITEM2,Menu.NONE,"# "+secondTag);
        menu.add(Menu.NONE,MENU_ITEM_ITEM3,Menu.NONE,"# "+thirdTag);
        menu.add(Menu.NONE,MENU_ITEM_ITEM4,Menu.NONE,"# "+fourthTag);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handling navigation view item clicks here.
        switch (item.getItemId()){
            case MENU_ITEM_ITEM1:
                setupPager(firstTag);
                break;
            case MENU_ITEM_ITEM2:
                setupPager(secondTag);
                break;
            case MENU_ITEM_ITEM3:
                setupPager(thirdTag);
                break;
            case MENU_ITEM_ITEM4:
                setupPager(fourthTag);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
