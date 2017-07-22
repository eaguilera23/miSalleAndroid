package monk.com.mx.misalleandroid.presenter;

import monk.com.mx.misalleandroid.model.InformationManager;
import monk.com.mx.misalleandroid.view.LoginActivity;

/**
 * Created by edago on 7/2/17.
 */
public class LoginPresenter {

    private final LoginActivity activity;

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

    public boolean isSessionActive() {
        InformationManager informationManager = new InformationManager();
        return informationManager.getSession();
    }
}
