package monk.com.mx.misalleandroid.view.helpers;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

import monk.com.mx.misalleandroid.MyApplication;
import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.presenter.GradesPresenter;
import monk.com.mx.misalleandroid.view.AdvertisingFragment;
import monk.com.mx.misalleandroid.view.GradesFragment;
import monk.com.mx.misalleandroid.view.HomeFragment;
import monk.com.mx.misalleandroid.view.InformationFragment;
import monk.com.mx.misalleandroid.view.MainActivity;
import monk.com.mx.misalleandroid.view.ScheduleFragment;

/**
 * Created by edago on 7/1/17.
 */
public class MainNavigationViewListener implements NavigationView.OnNavigationItemSelectedListener, ActionBar.OnNavigationListener {

    private MainActivity mainActivity;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Fragment fragment;
    private FirebaseAnalytics firebaseAnalytics;

    public MainNavigationViewListener(MainActivity activity){
        mainActivity = activity;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        fragmentManager = mainActivity.getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        firebaseAnalytics = FirebaseAnalytics.getInstance(mainActivity);
        Bundle event = new Bundle();

        switch (id){
            case R.id.nav_home:
                fragment = fragmentManager.findFragmentByTag("home");

                if (fragment == null || !fragment.isVisible()) {
                    AdvertisingFragment _advertising = new AdvertisingFragment();
                    fragmentTransaction.replace(R.id.frag_ad_main, _advertising, "advertising");
                    mainActivity.getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                    mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(true);
                    mainActivity.getSupportActionBar().setTitle(R.string.title_home);
                    HomeFragment _home = new HomeFragment();
                    fragmentTransaction.replace(R.id.frag_content_main, _home, "home");
                    fragment = null;
                    event.putString("item", "home");
                }
                break;
            case R.id.nav_grades:
                fragment = fragmentManager.findFragmentByTag("grades");

                if (fragment == null || !fragment.isVisible()) {
                    AdvertisingFragment _advertising = new AdvertisingFragment();
                    fragmentTransaction.replace(R.id.frag_ad_main, _advertising, "advertising");
                    mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
                    createDropDown();
                    Bundle bundle = new Bundle();
                    bundle.putInt("period", 0);
                    GradesFragment _grades = new GradesFragment();
                    _grades.setArguments(bundle);
                    fragmentTransaction.replace(R.id.frag_content_main, _grades, "grades");
                    fragment = null;
                    event.putString("item", "grades");
                }
                break;
            case R.id.nav_schedule:
                fragment = fragmentManager.findFragmentByTag("schedule");

                if (fragment == null || !fragment.isVisible()) {
                    AdvertisingFragment _advertising = new AdvertisingFragment();
                    fragmentTransaction.replace(R.id.frag_ad_main, _advertising, "advertising");
                    mainActivity.getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                    mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(true);
                    mainActivity.getSupportActionBar().setTitle(R.string.title_schedule);

                    ScheduleFragment _horario = new ScheduleFragment();
                    fragmentTransaction.replace(R.id.frag_content_main, _horario, "schedule");
                    fragment = null;
                    event.putString("item", "schedule");
                }
                break;
            case R.id.nav_info:
                fragment = fragmentManager.findFragmentByTag("info");

                if (fragment == null || !fragment.isVisible()){
                    mainActivity.getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                    mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(true);
                    mainActivity.getSupportActionBar().setTitle(R.string.title_info);

                    InformationFragment _info = new InformationFragment();
                    fragmentTransaction.replace(R.id.frag_content_main, _info, "info");
                    fragment = null;
                    event.putString("item", "info");
                }
                break;
            default:
                break;
        }

        firebaseAnalytics.logEvent("navigation", event);
        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) mainActivity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void createDropDown() {
        String[] months = MyApplication.getContext().getResources().getStringArray(R.array.months);
        GradesPresenter gradesPresenter = new GradesPresenter();
        ArrayList<String> items = gradesPresenter.getPeriodosName(months);

        //Get the action bar
        ActionBar actionBar = mainActivity.getSupportActionBar();
        //Adapter for spinner
        ArrayAdapter dataAdapter = new ArrayAdapter<>(mainActivity, android.R.layout.simple_spinner_item, items);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Create spinner
        actionBar.setListNavigationCallbacks(dataAdapter, this);
        //Show spinner
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {

        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        GradesFragment grades = new GradesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("period", itemPosition);
        grades.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.frag_content_main, grades, "grades").commit();
        return false;
    }
}
