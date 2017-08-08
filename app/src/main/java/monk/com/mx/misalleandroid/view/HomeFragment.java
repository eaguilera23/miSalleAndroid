package monk.com.mx.misalleandroid.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import monk.com.mx.misalleandroid.MyApplication;
import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.domain.FontManager;
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
            _txv_creditos_cultural, txv_creditos_deportivo, txv_creditos_social,
            _txv_pay_due_date_date_home, _txv_pay_due_date_name_home, _txv_remaining_days_home,
            _txv_payment_header, _txv_refresh_creditos;
    LinearLayout _txv_payment_line_header;
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
        _txv_pay_due_date_date_home = (TextView)v.findViewById(R.id.txv_due_date_date_home);
        _txv_pay_due_date_name_home = (TextView)v.findViewById(R.id.txv_pay_due_date_name_home);
        _txv_remaining_days_home = (TextView)v.findViewById(R.id.txv_remaining_days_home);
        _txv_payment_header = (TextView)v.findViewById(R.id.txv_payment_header);
        _txv_payment_line_header = (LinearLayout)v.findViewById(R.id.txv_payment_line_header);

        _txv_refresh_creditos = (TextView)v.findViewById(R.id._txv_refresh_creditos_home);
        _txv_refresh_creditos.setTypeface(FontManager.getTypeface(FontManager.FONTAWESOME));

        homePresenter = new HomePresenter(this);

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().invalidateOptionsMenu();
    }

    public void setNextClass(Clase nextClass) {
        _txv_next_class_name.setText(nextClass.getMateria().getNombre());
        txv_next_class_hour.setText(StringFormater.ToHour(nextClass.getHora_inicio(), nextClass.getHora_final()));
        txv_next_class_teacher.setText(nextClass.getProfesor().getNombre());
    }

    public void setCreditos(ArrayList<Credito> creditos) {
        _txv_refresh_creditos.setText("");
        txv_creditos_social.setText(String.valueOf(creditos.get(0).getActuales()));
        _txv_creditos_cultural.setText(String.valueOf(creditos.get(1).getActuales()));
        txv_creditos_deportivo.setText(String.valueOf(creditos.get(2).getActuales()));
    }

    public void setNextPayment(String payment, String month, int daysBetween) {
        _txv_pay_due_date_date_home.setText("vence: " + payment);
        _txv_pay_due_date_name_home.setText(month);
        _txv_remaining_days_home.setText(String.valueOf(daysBetween));
        setColorPayment(daysBetween);

    }

    private void setColorPayment(int daysBetween) {
        int color;
        if (daysBetween > 15){
            color = ContextCompat.getColor(MyApplication.getContext(), R.color.colorGreen);
            _txv_remaining_days_home.setBackgroundResource(R.drawable.circle_green);
        }else if (daysBetween <= 15 && daysBetween >= 5){
            color = ContextCompat.getColor(MyApplication.getContext(), R.color.colorBlue);
            _txv_remaining_days_home.setBackgroundResource(R.drawable.circle_blue);
        }else{
            color = ContextCompat.getColor(MyApplication.getContext(), R.color.colorRed);
            _txv_remaining_days_home.setBackgroundResource(R.drawable.circle_red);
        }
        _txv_payment_header.setBackgroundColor(color);
        _txv_payment_line_header.setBackgroundColor(color);
        _txv_pay_due_date_date_home.setTextColor(color);

    }
}
