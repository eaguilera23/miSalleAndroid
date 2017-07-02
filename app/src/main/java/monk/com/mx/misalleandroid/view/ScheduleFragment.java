package monk.com.mx.misalleandroid.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.view.helpers.SchedulePageAdapter;

/**
 * Created by edago on 7/1/17.
 */
public class ScheduleFragment extends Fragment {

    public ScheduleFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);

        TabLayout _tab_layout = (TabLayout)v.findViewById(R.id.tab_days_of_week_schedule);
        ViewPager _pgr_week = (ViewPager)v.findViewById(R.id.viewpgr_content_schedule);

        PagerAdapter pagerAdapter = new SchedulePageAdapter(getFragmentManager());

        _pgr_week.setAdapter(pagerAdapter);
        _tab_layout.setupWithViewPager(_pgr_week);

        return v;
    }
}
