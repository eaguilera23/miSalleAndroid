package monk.com.mx.misalleandroid.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import monk.com.mx.misalleandroid.MyApplication;
import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.presenter.InformationPresenter;

/**
 * Created by edago on 8/7/17.
 */

public class InformationFragment extends Fragment {
    private InformationPresenter informationPresenter;
    public InformationFragment(){
        informationPresenter = new InformationPresenter(this);
    }

    private EditText txt_feedback;
    private Button btn_send_feedback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info, container, false);

        txt_feedback = (EditText)v.findViewById(R.id.txt_feedback_info);
        btn_send_feedback = (Button)v.findViewById(R.id.btn_send_feedback_info);

        btn_send_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedback = txt_feedback.getText().toString();
                informationPresenter.sendFeedback(feedback);
            }
        });

        return v;
    }

    public void onEmptyFeedback() {
        Toast toast = Toast.makeText(MyApplication.getContext(), "Mándanos lo que quieras, pero mándanos algo", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onFeedbackSent() {
        txt_feedback.setText("");
        Toast toast = Toast.makeText(MyApplication.getContext(), "¡Gracias!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onErrorRequest() {
        Toast toast = Toast.makeText(MyApplication.getContext(), "Algo no funcionó. Por favor inténtalo más tarde.\nTu opinión es muy importante", Toast.LENGTH_LONG);
        toast.show();
    }
}
