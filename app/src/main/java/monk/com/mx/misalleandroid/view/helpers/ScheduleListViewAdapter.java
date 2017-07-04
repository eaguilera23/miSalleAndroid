package monk.com.mx.misalleandroid.view.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import monk.com.mx.misalleandroid.MyApplication;
import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.domain.StringFormater;
import monk.com.mx.misalleandroid.model.dataModels.Clase;
import monk.com.mx.misalleandroid.presenter.SchedulePresenter;

/**
 * Created by edago on 7/2/17.
 */
public class ScheduleListViewAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    ArrayList<Clase> schedule;

    public ScheduleListViewAdapter(LayoutInflater pInflater, ArrayList<Clase> pSchedule){
        inflater = pInflater;
        schedule = pSchedule;
    }
    @Override
    public int getCount() {
        return schedule.size();
    }

    @Override
    public Object getItem(int i) {
        return schedule.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View _v = inflater.inflate(R.layout.list_item_schedule, null, true);

        TextView _txv_hour = (TextView)_v.findViewById(R.id.txv_hour_item_schedule);
        TextView _txv_class = (TextView)_v.findViewById(R.id.txv_class_name_item_schedule);
        TextView _txv_teacher = (TextView)_v.findViewById(R.id.txv_teacher_name_item_schedule);
        TextView _txv_absences = (TextView)_v.findViewById(R.id.txv_absences_item_schedule);

        Clase clase = schedule.get(i);

        _txv_hour.setText(StringFormater.ToHour(clase.getHora_inicio(), clase.getHora_final()));
        _txv_hour.setTextColor(clase.getMateria().getColor());
        _txv_class.setText(clase.getMateria().getNombre());
        _txv_teacher.setText(clase.getProfesor().getNombre());

        return _v;
    }
}
