package monk.com.mx.misalleandroid.view.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;

import monk.com.mx.misalleandroid.R;

/**
 * Created by edago on 7/2/17.
 */
public class GradesExListViewAdapter extends BaseExpandableListAdapter {

    LayoutInflater inflater;

    public GradesExListViewAdapter(LayoutInflater pInflater){
        inflater = pInflater;
    }
    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
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

        ImageView _img_arrow = (ImageView)_v.findViewById(R.id.img_arrow_list_grades);

        if(isExpanded){
            _img_arrow.setImageResource(R.mipmap.ic_arrow_up);
        }
        else {
            _img_arrow.setImageResource(R.mipmap.ic_arrow_down);
        }

        return _v;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        View _v = inflater.inflate(R.layout.list_item_grades_partials, null, true);

        return _v;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
