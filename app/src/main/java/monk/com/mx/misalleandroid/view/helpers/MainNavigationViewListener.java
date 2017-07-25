package monk.com.mx.misalleandroid.view.helpers;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import monk.com.mx.misalleandroid.MyApplication;
import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.presenter.GradesPresenter;
import monk.com.mx.misalleandroid.view.AdvertisingFragment;
import monk.com.mx.misalleandroid.view.GradesFragment;
import monk.com.mx.misalleandroid.view.HomeFragment;
import monk.com.mx.misalleandroid.view.MainActivity;
import monk.com.mx.misalleandroid.view.ScheduleFragment;

/**
 * Created by edago on 7/1/17.
 */
public class MainNavigationViewListener implements NavigationView.OnNavigationItemSelectedListener, ActionBar.OnNavigationListener {

    private MainActivity mainActivity;
    FragmentTransaction fragmentTransaction;

    public MainNavigationViewListener(MainActivity activity){
        mainActivity = activity;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        fragmentTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
        AdvertisingFragment _advertising = new AdvertisingFragment();
        fragmentTransaction.replace(R.id.frag_ad_main, _advertising, "advertising");

        switch (id){
            case R.id.nav_home:
                mainActivity.getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(true);
                mainActivity.getSupportActionBar().setTitle(R.string.title_home);
                HomeFragment _home = new HomeFragment();
                fragmentTransaction.replace(R.id.frag_content_main, _home, "home");
                break;
            case R.id.nav_grades:
                mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
                createDropDown();
                Bundle bundle = new Bundle();
                bundle.putInt("period", 0);
                GradesFragment _grades = new GradesFragment();
                _grades.setArguments(bundle);
                fragmentTransaction.replace(R.id.frag_content_main, _grades, "grades");
                break;
            case R.id.nav_schedule:
                mainActivity.getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(true);
                mainActivity.getSupportActionBar().setTitle(R.string.title_schedule);

                ScheduleFragment _horario = new ScheduleFragment();
                fragmentTransaction.replace(R.id.frag_content_main, _horario, "schedule");
                break;
            default:
                break;
        }

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
