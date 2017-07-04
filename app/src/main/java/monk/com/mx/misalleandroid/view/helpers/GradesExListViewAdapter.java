package monk.com.mx.misalleandroid.view.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.model.dataModels.Boleta;

/**
 * Created by edago on 7/2/17.
 */
public class GradesExListViewAdapter extends BaseExpandableListAdapter {

    LayoutInflater inflater;
    ArrayList<Boleta> boletas;

    public GradesExListViewAdapter(LayoutInflater pInflater, ArrayList<Boleta> content){
        inflater = pInflater;
        boletas = content;
    }
    @Override
    public int getGroupCount() {
        return boletas.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return boletas.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean isExpanded, View view, ViewGroup viewGroup) {

        View _v = inflater.inflate(R.layout.list_item_grades, null, true);

        TextView _txv_class = (TextView)_v.findViewById(R.id.txv_class_list_grades);
        TextView _txv_teacher = (TextView)_v.findViewById(R.id.txv_teacher_list_grades);
        TextView _txv_final_grade = (TextView)_v.findViewById(R.id.txv_final_grade_list_grades);
        TextView _txv_absences = (TextView)_v.findViewById(R.id.txv_absences_list_grades);
        ImageView _img_arrow = (ImageView)_v.findViewById(R.id.img_arrow_list_grades);

        if(isExpanded){
            _img_arrow.setImageResource(R.mipmap.ic_arrow_up);
        }
        else {
            _img_arrow.setImageResource(R.mipmap.ic_arrow_down);
        }

        int[] colors = _v.getResources().getIntArray(R.array.colors_class);

        _txv_class.setTextColor(colors[i]);

        _txv_class.setText(boletas.get(i).getMateria().getNombre());
        _txv_teacher.setText(boletas.get(i).getProfesor().getNombre());
        _txv_final_grade.setText(String.valueOf(boletas.get(i).getParciales().get(4).getCalificacion()));

        return _v;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        View _v = inflater.inflate(R.layout.list_item_grades_partials, null, true);

        TextView _txv_partial1 = (TextView)_v.findViewById(R.id.txv_partial1_list_grades);
        TextView _txv_partial2 = (TextView)_v.findViewById(R.id.txv_partial2_list_grades);
        TextView _txv_partial3 = (TextView)_v.findViewById(R.id.txv_partial3_list_grades);

        _txv_partial1.setText(String.valueOf(boletas.get(i).getParciales().get(0).getCalificacion()));
        _txv_partial2.setText(String.valueOf(boletas.get(i).getParciales().get(1).getCalificacion()));
        _txv_partial3.setText(String.valueOf(boletas.get(i).getParciales().get(2).getCalificacion()));

        return _v;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
