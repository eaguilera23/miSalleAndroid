package monk.com.mx.misalleandroid.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import monk.com.mx.misalleandroid.MyApplication;
import monk.com.mx.misalleandroid.model.InformationManager;
import monk.com.mx.misalleandroid.model.ScrapperRequest;
import monk.com.mx.misalleandroid.model.dataModels.Alumno;
import monk.com.mx.misalleandroid.model.dataModels.Usuario;
import monk.com.mx.misalleandroid.view.LoadingActivity;

/**
 * Created by edago on 7/2/17.
 */
public class LoadingPresenter {

    private LoadingActivity loadingActivity;
    private String _matricula, _password;
    private InformationManager informationManager;
    private ScrapperRequest scrapperRequest;

    public LoadingPresenter(LoadingActivity loadingActivity, String pMatricula, String pPassword) {
        this.loadingActivity = loadingActivity;
        this._matricula = pMatricula;
        this._password = pPassword;
    }

    public void LoadInformation(){
        Usuario user = new Usuario(_matricula, _password);
        scrapperRequest = new ScrapperRequest();
        scrapperRequest.GetAlumnoRequest(user, this);
    }

    public void onRequestResponse(Alumno alumno){
        informationManager = new InformationManager();
        informationManager.setUserInformation(alumno);

        Context context = MyApplication.getContext();
        String preferencesFile = MyApplication.getPreferencesString();
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesFile, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("matricula", _matricula);
        editor.commit();

        loadingActivity.onSuccessfulLoading();
    }

    public void onErrorLoading(String error){
        loadingActivity.onErrorLoading(error);
    }

    public void setSession() {
        informationManager.setSession(true);
    }
}
