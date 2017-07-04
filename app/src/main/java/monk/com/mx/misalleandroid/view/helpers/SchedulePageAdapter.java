package monk.com.mx.misalleandroid.view.helpers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import monk.com.mx.misalleandroid.model.dataModels.Clase;
import monk.com.mx.misalleandroid.presenter.SchedulePresenter;
import monk.com.mx.misalleandroid.view.ScheduleListFragment;

/**
 * Created by edago on 7/2/17.
 */
public class SchedulePageAdapter extends FragmentStatePagerAdapter {

    SchedulePresenter schedulePresenter;
    String[] week;

    public SchedulePageAdapter(FragmentManager fm, SchedulePresenter presenter, String[] pWeek) {
        super(fm);

        schedulePresenter = presenter;
        week = pWeek;
    }

    @Override
    public Fragment getItem(int position) {
        ArrayList<Clase> scheduleForDay = schedulePresenter.getScheduleForDay(position + 1);
        ScheduleListFragment scheduleListFragment = new ScheduleListFragment();
        scheduleListFragment.setSchedule(scheduleForDay);

        return scheduleListFragment;
    }

    @Override
    public int getCount() {
        return week.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return week[position];
    }
}
