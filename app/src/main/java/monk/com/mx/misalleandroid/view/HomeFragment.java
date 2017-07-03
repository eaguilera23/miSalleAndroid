package monk.com.mx.misalleandroid.view;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.domain.StringFormater;
import monk.com.mx.misalleandroid.model.dataModels.Clase;
import monk.com.mx.misalleandroid.model.dataModels.Credito;
import monk.com.mx.misalleandroid.presenter.HomePresenter;

/**
 * Created by edago on 7/1/17.
 */
public class HomeFragment extends Fragment {

    HomePresenter homePresenter;

    public HomeFragment() {

    }

    TextView _txv_next_class_name, txv_next_class_hour, txv_next_class_teacher,
            _txv_creditos_cultural, txv_creditos_deportivo, txv_creditos_social;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Set orientation to portrait only
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        _txv_next_class_name = (TextView)v.findViewById(R.id.txv_next_class_name_home);
        txv_next_class_hour = (TextView)v.findViewById(R.id.txv_next_class_hour_home);
        txv_next_class_teacher = (TextView)v.findViewById(R.id.txv_next_class_teacher_home);
        _txv_creditos_cultural = (TextView)v.findViewById(R.id.txv_creditos_cultural_home);
        txv_creditos_deportivo = (TextView)v.findViewById(R.id.txv_creditos_deportivo_home);
        txv_creditos_social = (TextView)v.findViewById(R.id.txv_creditos_social_home);

        homePresenter = new HomePresenter(this);

        return v;
    }

    public void setNextClass(Clase nextClass) {
        _txv_next_class_name.setText(nextClass.getMateria().getNombre());
        txv_next_class_hour.setText(StringFormater.ToHour(nextClass.getHora_inicio(), nextClass.getHora_final()));
        txv_next_class_teacher.setText(nextClass.getProfesor().getNombre());
    }

    public void setCreditos(ArrayList<Credito> creditos) {
        txv_creditos_social.setText(String.valueOf(creditos.get(0).getActuales()));
        _txv_creditos_cultural.setText(String.valueOf(creditos.get(1).getActuales()));
        txv_creditos_deportivo.setText(String.valueOf(creditos.get(2).getActuales()));
    }
}
