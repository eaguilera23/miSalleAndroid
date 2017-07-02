package monk.com.mx.misalleandroid.view.helpers;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.view.HomeFragment;
import monk.com.mx.misalleandroid.view.ScheduleFragment;

/**
 * Created by edago on 7/1/17.
 */
public class MainNavigationViewListener implements NavigationView.OnNavigationItemSelectedListener {

    private AppCompatActivity _activity;
    FragmentTransaction fragmentTransaction;

    public MainNavigationViewListener(AppCompatActivity activity){
        _activity = activity;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        fragmentTransaction = _activity.getSupportFragmentManager().beginTransaction();

        switch (id){
            case R.id.nav_home:
                _activity.getSupportActionBar().setDisplayShowTitleEnabled(true);
                _activity.getSupportActionBar().setTitle(R.string.title_home);
                HomeFragment _home = new HomeFragment();
                fragmentTransaction.replace(R.id.frag_content_main, _home, "home");
                break;
            case R.id.nav_grades:
                break;
            case R.id.nav_schedule:
                _activity.getSupportActionBar().setDisplayShowTitleEnabled(true);
                _activity.getSupportActionBar().setTitle(R.string.title_schedule);

                ScheduleFragment _horario = new ScheduleFragment();
                fragmentTransaction.replace(R.id.frag_content_main, _horario, "schedule");
                break;
            case R.id.nav_sign_out:
                break;
            default:
                break;
        }

        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) _activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
