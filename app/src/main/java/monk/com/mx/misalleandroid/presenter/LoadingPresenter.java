package monk.com.mx.misalleandroid.presenter;

import monk.com.mx.misalleandroid.model.InformationManager;
import monk.com.mx.misalleandroid.view.LoadingActivity;

/**
 * Created by edago on 7/2/17.
 */
public class LoadingPresenter {

    private static LoadingActivity loadingActivity;
    private String _matricula, _password;

    public LoadingPresenter(LoadingActivity loadingActivity, String pMatricula, String pPassword) {
        this.loadingActivity = loadingActivity;
        this._matricula = pMatricula;
        this._password = pPassword;
    }

    public void LoadInformation(){
        InformationManager informationManager = new InformationManager();

        informationManager.RequestUserInformation(_matricula, _password);
    }

    public static void onSuccessfulLoading(){
        loadingActivity.onSuccessfulLoading();
    }

    public static void onErrorLoading(String error){
        loadingActivity.onErrorLoading(error);
    }
}
