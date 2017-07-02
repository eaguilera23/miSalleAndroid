package monk.com.mx.misalleandroid.view;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import monk.com.mx.misalleandroid.R;

/**
 * Created by edago on 7/1/17.
 */
public class HomeFragment extends Fragment {
    public HomeFragment() {

    }

    TextView _txv_next_class_name, txv_next_class_hour, txv_next_class_teacher,
            _txv_creditos_cultural, txv_creditos_deportivo, txv_creditos_social;
    AppCompatActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Set orientation to portrait only
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // #############################
        // ## Esto va en el presenter ##
        // #############################
//        activity = (AppCompatActivity) getActivity();
//
//        _txv_next_class_name = (TextView)v.findViewById(R.id.txv_next_class_name_home);
//        txv_next_class_hour = (TextView)v.findViewById(R.id.txv_next_class_hour_home);
//        txv_next_class_teacher = (TextView)v.findViewById(R.id.txv_next_class_teacher_home);
//        _txv_creditos_cultural = (TextView)v.findViewById(R.id.txv_creditos_cultural_home);
//        txv_creditos_deportivo = (TextView)v.findViewById(R.id.txv_creditos_deportivo_home);
//        txv_creditos_social = (TextView)v.findViewById(R.id.txv_creditos_social_home);

        // ESTO VA EN DOMAIN
        //SetFirstClass();
        //SetCredits();

        return v;
    }
}
