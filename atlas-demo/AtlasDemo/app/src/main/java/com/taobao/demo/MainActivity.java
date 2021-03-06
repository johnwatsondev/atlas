package com.taobao.demo;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.middleware.dialog.Dialog;
import com.taobao.android.ActivityGroupDelegate;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private ActivityGroupDelegate mActivityDelegate;
    private ViewGroup mActivityGroupContainer;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchToActivity("home","com.taobao.firstbundle.FirstBundleActivity");
                    return true;
                case R.id.navigation_dashboard:
                    switchToActivity("second","com.taobao.secondbundle.SecondBundleActivity");
                    return true;
                case R.id.navigation_notifications:
//                    Intent intent3 = new Intent();
//                    intent3.setClassName(getBaseContext(),"com.taobao.firstBundle.FirstBundleActivity");
//                    mActivityDelegate.execStartChildActivityInternal(mActivityGroupContainer,"third",intent3);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.e("ddddd","dsfsfsf");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        ((BottomNavigationView)findViewById(R.id.navigation)).setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mActivityDelegate = new ActivityGroupDelegate(this,savedInstanceState);
        mActivityGroupContainer = (ViewGroup) findViewById(R.id.content);
        switchToActivity("home","com.taobao.firstbundle.FirstBundleActivity");
    }

    public void switchToActivity(String key,String activityName){
        Intent intent = new Intent();
        intent.setClassName(getBaseContext(),activityName);
        mActivityDelegate.startChildActivity(mActivityGroupContainer,key,intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ContentResolver resolver = getContentResolver();
            resolver.query(Uri.parse("content://com.test.abc/ccc"),null,null,null,null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        }
//        else
            if (id == R.id.nav_slideshow) {
            Intent intent = new Intent();
            intent.setClassName(this,"com.taobao.demo.UpdateDemoActivity");
            startActivity(intent);

        } else if (id == R.id.nav_manage) {

            Intent intent = new Intent();
            intent.setClassName(this,"com.taobao.demo.RemoteDemoActivity");
            startActivity(intent);

        } else if (id == R.id.awo_manager) {
            Dialog dialog = new Dialog(this,"???bundle??????",
                    "1????????????????????????????????????\n\n"+
                     "2???????????????bundle??????????????????????????????????????????????????????\n\n"+
                            "3???bundle???????????????????????? ../gradlew clean assemblePatchDebug,??????????????????????????????????????????????????????");

            dialog.show();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
