package monk.com.mx.misalleandroid.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;

import monk.com.mx.misalleandroid.MyApplication;
import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.model.dataModels.Boleta;
import monk.com.mx.misalleandroid.model.dataModels.Periodo;
import monk.com.mx.misalleandroid.presenter.GradesPresenter;
import monk.com.mx.misalleandroid.view.helpers.GradesExListViewAdapter;

/**
 * Created by edago on 7/2/17.
 */
public class GradesFragment extends Fragment {

    GradesPresenter gradesPresenter;
    ExpandableListView _lst_grades;
    SwipeRefreshLayout swipeRefreshLayout;
    GradesExListViewAdapter gradesAdapter;
    int period;

    public GradesFragment() {
        gradesPresenter = new GradesPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View _v = inflater.inflate(R.layout.fragment_grades, container, false);

        period = getArguments().getInt("period");
        swipeRefreshLayout = (SwipeRefreshLayout) _v.findViewById(R.id.refresh_grades_container);

        if (period == 0) {
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    gradesPresenter.UpdatePeriodos();
                }
            });
        }else{
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }

        _lst_grades = (ExpandableListView)_v.findViewById(R.id.lst_grades);
        ArrayList<Boleta> grades = gradesPresenter.getGrades(period);
        gradesAdapter = new GradesExListViewAdapter(inflater, grades);

        _lst_grades.setAdapter(gradesAdapter);

        return _v;
    }

    public void UpdatePeriodsView(){
        MainActivity activity = (MainActivity)getActivity();
//        MenuItem item = activity.getNavigationView().getMenu().findItem(R.id.nav_grades);
//        item.setChecked(true);
//
//        activity.getNavigationViewListener().onNavigationItemSelected(item);
//        activity.
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        GradesFragment newFragment = new GradesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("period", period);
        newFragment.setArguments(bundle);
        swipeRefreshLayout.setRefreshing(false);
        fragmentManager.beginTransaction().replace(R.id.frag_content_main, newFragment, "grades").commit();
    }

    public void OnRefreshError() {
        swipeRefreshLayout.setRefreshing(false);
        Toast toast = Toast.makeText(MyApplication.getContext(), "Hubo un error de conexión.\nInténtalo más tarde", Toast.LENGTH_SHORT);
        toast.show();
    }
}
