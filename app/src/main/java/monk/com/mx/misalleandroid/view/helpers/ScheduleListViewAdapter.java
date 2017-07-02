package monk.com.mx.misalleandroid.view.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import monk.com.mx.misalleandroid.R;

/**
 * Created by edago on 7/2/17.
 */
public class ScheduleListViewAdapter extends BaseAdapter {

    private final LayoutInflater inflater;

    public ScheduleListViewAdapter(LayoutInflater pInflater){
        inflater = pInflater;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View _v = inflater.inflate(R.layout.list_item_schedule, null, true);

        TextView _txv_hour = (TextView)_v.findViewById(R.id.txv_hour_item_schedule);
        TextView _txv_class = (TextView)_v.findViewById(R.id.txv_class_name_item_schedule);
        TextView _txv_teacher = (TextView)_v.findViewById(R.id.txv_teacher_name_item_schedule);
        TextView _txv_absences = (TextView)_v.findViewById(R.id.txv_absences_item_schedule);

        return _v;
    }
}
