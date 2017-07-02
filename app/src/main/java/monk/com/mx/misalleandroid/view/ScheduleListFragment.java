package monk.com.mx.misalleandroid.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.view.helpers.ScheduleListViewAdapter;

/**
 * Created by edago on 7/2/17.
 */
public class ScheduleListFragment extends Fragment{

    public ScheduleListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View _v = inflater.inflate(R.layout.fragment_schedule_list, container, false);

        ListView _list_schedule = (ListView)_v.findViewById(R.id.lst_schedule);

        _list_schedule.setAdapter(new ScheduleListViewAdapter(inflater));

        return _v;
    }
}
