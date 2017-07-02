package monk.com.mx.misalleandroid.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.view.helpers.GradesExListViewAdapter;

/**
 * Created by edago on 7/2/17.
 */
public class GradesFragment extends Fragment {

    public GradesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View _v = inflater.inflate(R.layout.fragment_grades, container, false);

        ExpandableListView _lst_grades = (ExpandableListView)_v.findViewById(R.id.lst_grades);
        _lst_grades.setAdapter(new GradesExListViewAdapter(inflater));

        return _v;
    }
}
