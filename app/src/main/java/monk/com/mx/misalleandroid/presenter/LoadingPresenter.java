package monk.com.mx.misalleandroid.presenter;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
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
    private FirebaseAuth firebaseAuth;

    public LoadingPresenter(LoadingActivity loadingActivity, String pMatricula, String pPassword) {
        this.loadingActivity = loadingActivity;
        this._matricula = pMatricula;
        this._password = pPassword;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public void LoadInformation(){
        Usuario user = new Usuario(_matricula, _password);
        scrapperRequest = new ScrapperRequest();
        scrapperRequest.GetAlumnoRequest(user, this);
    }

    public void onRequestResponse(Alumno alumno){
        informationManager = new InformationManager();
        informationManager.setUserInformation(alumno);
        informationManager.setMatriculaLocal(_matricula);
        if (alumno.getNuevo_ingreso() == 1){
            SignUpFirebase();
        }else{
            SignInFirebase();
        }
    }

    private void SignUpFirebase() {
        String email = getFirebaseEmail(_matricula);
        firebaseAuth.createUserWithEmailAndPassword(email, _password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            loadingActivity.onSuccessfulLoading();
                        }
                    }
                });
    }

    private void SignInFirebase() {
        String email = getFirebaseEmail(_matricula);
        firebaseAuth.signInWithEmailAndPassword(email, _password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            loadingActivity.onSuccessfulLoading();
                        }
                    }
                });
    }

    public void onErrorLoading(String error){
        loadingActivity.onErrorLoading(error);
    }

    private String getFirebaseEmail(String matricula){
        return matricula + "@monk.com.mx";
    }
}
