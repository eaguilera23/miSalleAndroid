package monk.com.mx.misalleandroid.view;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView img_fb;
    private static final String FB_PAGE = "https://www.facebook.com/misallemx/";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info, container, false);

        txt_feedback = (EditText)v.findViewById(R.id.txt_feedback_info);
        btn_send_feedback = (Button)v.findViewById(R.id.btn_send_feedback_info);
        img_fb = (ImageView)v.findViewById(R.id.img_fb_info);

        btn_send_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_send_feedback.setText("Enviando...");
                String feedback = txt_feedback.getText().toString();
                informationPresenter.sendFeedback(feedback);
            }
        });

        img_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendToFbApp();
            }
        });

        return v;
    }

    private void SendToFbApp() {
        Uri uri = Uri.parse(FB_PAGE);

        PackageManager pm = MyApplication.getContext().getPackageManager();

        try {
            ApplicationInfo applicationinfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationinfo.enabled){
                uri = uri.parse("fb://facewebmodal/f?href=" + FB_PAGE);
            }
        } catch (PackageManager.NameNotFoundException ignored) {

        }

        Intent fbIntent = new Intent(Intent.ACTION_VIEW);
        fbIntent.setData(uri);
        startActivity(fbIntent);
    }

    public void onEmptyFeedback() {
        Toast toast = Toast.makeText(MyApplication.getContext(), "Te escuchamos con atención", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onFeedbackSent() {
        txt_feedback.setText("");
        btn_send_feedback.setText("Enviar");
        Toast toast = Toast.makeText(MyApplication.getContext(), "¡Gracias!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onErrorRequest() {
        btn_send_feedback.setText("Enviar");
        Toast toast = Toast.makeText(MyApplication.getContext(), "Algo no funcionó. Por favor inténtalo más tarde.\nTu opinión es muy importante", Toast.LENGTH_LONG);
        toast.show();
    }

    public void OnRecentFeedback() {
        btn_send_feedback.setText("Enviar");
        Toast toast = Toast.makeText(MyApplication.getContext(), "Te escuchamos con atención", Toast.LENGTH_LONG);
        toast.show();
    }
}
