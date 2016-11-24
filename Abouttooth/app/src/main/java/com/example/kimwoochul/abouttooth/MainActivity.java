package com.example.kimwoochul.abouttooth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kimwoochul.abouttooth.NavigationTabs.AboutToothActivity;
import com.example.kimwoochul.abouttooth.NavigationTabs.DenticeActivity;
import com.example.kimwoochul.abouttooth.NavigationTabs.MessageActivity;
import com.example.kimwoochul.abouttooth.NavigationTabs.NoticeActivity;
import com.example.kimwoochul.abouttooth.NavigationTabs.SettingActivity;
import com.example.kimwoochul.abouttooth.TabTools.TabFragment;

public class MainActivity extends ActionBarActivity //implements  NavigationView.OnNavigationItemSelectedListener
{
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

      //  mNavigationView.setNavigationItemSelectedListener(this);

        /**
         * Setup Drawer Toggle of the Toolbar
         */
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

  //      mDrawerLayout.setDrawerListener(mDrawerToggle);

      //  mDrawerToggle.syncState();

    }
//    public boolean onNavigationItemSelected(MenuItem item)
//    {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_item_notice)
//        {
//            startActivity(new Intent(MainActivity.this, NoticeActivity.class));
//        }
//        else if (id == R.id.nav_item_message)
//        {
//            startActivity(new Intent(MainActivity.this, MessageActivity.class));
//        }
//        else if (id == R.id.nav_item_dentice)
//        {
//            startActivity(new Intent(MainActivity.this, DenticeActivity.class));
//        }
//        else if (id == R.id.nav_item_abouttooth)
//        {
//            startActivity(new Intent(MainActivity.this, AboutToothActivity.class));
//        }
//        else if (id == R.id.nav_item_setting)
//        {
//            startActivity(new Intent(MainActivity.this, SettingActivity.class));
//        }
//        else if (id == R.id.nav_item_logout)
//        {
//            Toast.makeText(getApplicationContext(), "로그아웃", Toast.LENGTH_SHORT);
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }
}
