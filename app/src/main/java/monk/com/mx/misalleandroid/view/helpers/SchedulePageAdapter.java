package monk.com.mx.misalleandroid.view.helpers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import monk.com.mx.misalleandroid.view.ScheduleListFragment;

/**
 * Created by edago on 7/2/17.
 */
public class SchedulePageAdapter extends FragmentStatePagerAdapter {

    public SchedulePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ScheduleListFragment _schedule_list = new ScheduleListFragment();

        return _schedule_list;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
