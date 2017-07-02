package monk.com.mx.misalleandroid.presenter;

import android.support.v7.app.AppCompatActivity;

import monk.com.mx.misalleandroid.view.LoginActivity;

/**
 * Created by edago on 7/2/17.
 */
public class LoginPresenter {

    private final LoginActivity activity;

    public LoginPresenter(LoginActivity pActivity) {
        this.activity = pActivity;
    }

    public void Auntenticate(String txt_matricula, String txt_password) {

        if (txt_matricula.isEmpty() || txt_password.isEmpty()){
            activity.onEmptyFields();
            return;
        }

    }
}
