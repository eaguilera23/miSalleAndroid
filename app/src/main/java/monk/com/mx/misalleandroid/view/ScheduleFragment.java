package monk.com.mx.misalleandroid.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import monk.com.mx.misalleandroid.MyApplication;
import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.model.dataModels.Clase;
import monk.com.mx.misalleandroid.presenter.SchedulePresenter;
import monk.com.mx.misalleandroid.view.helpers.SchedulePageAdapter;

/**
 * Created by edago on 7/1/17.
 */
public class ScheduleFragment extends Fragment {

    SchedulePresenter schedulePresenter;

    public ScheduleFragment(){
        schedulePresenter = new SchedulePresenter(this);
        schedulePresenter.setColors(MyApplication.getContext().getResources().getIntArray(R.array.colors_class));
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);

        TabLayout _tab_layout = (TabLayout)v.findViewById(R.id.tab_days_of_week_schedule);
        ViewPager _pgr_week = (ViewPager)v.findViewById(R.id.viewpgr_content_schedule);

        // Esto solo sirve para los que NO van el sábado.
        String[] week = getResources().getStringArray(R.array.letters_days_of_week);

        PagerAdapter pagerAdapter = new SchedulePageAdapter(getFragmentManager(), schedulePresenter, week);

        int position = schedulePresenter.getDayOfWeek();

        _pgr_week.setAdapter(pagerAdapter);
        _pgr_week.setCurrentItem(position);
        _tab_layout.setupWithViewPager(_pgr_week);

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().invalidateOptionsMenu();
    }
}
