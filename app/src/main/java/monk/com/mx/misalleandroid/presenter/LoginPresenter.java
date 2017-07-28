package monk.com.mx.misalleandroid.presenter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import monk.com.mx.misalleandroid.model.InformationManager;
import monk.com.mx.misalleandroid.view.LoginActivity;

/**
 * Created by edago on 7/2/17.
 */
public class LoginPresenter {

    private final LoginActivity activity;
    private FirebaseAuth firebaseAuth;

    public LoginPresenter(LoginActivity pActivity) {
        this.activity = pActivity;
    }

    public void CheckFields(String txt_matricula, String txt_password) {

        if (txt_matricula.isEmpty() || txt_password.isEmpty()){
            activity.onEmptyFields();
            return;
        }

        activity.navigateToLoadingActivity();
    }

    public void CheckSession() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser current_user = firebaseAuth.getCurrentUser();
        if (current_user != null) {
            activity.navigateToMainActivity();
        }
    }
}
