package monk.com.mx.misalleandroid.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import monk.com.mx.misalleandroid.MyApplication;
import monk.com.mx.misalleandroid.domain.JsonHandler;
import monk.com.mx.misalleandroid.model.dataModels.Alumno;
import monk.com.mx.misalleandroid.model.dataModels.AlumnoInfo;
import monk.com.mx.misalleandroid.model.dataModels.Anuncio;
import monk.com.mx.misalleandroid.model.dataModels.Clase;
import monk.com.mx.misalleandroid.model.dataModels.Click;
import monk.com.mx.misalleandroid.model.dataModels.Credito;
import monk.com.mx.misalleandroid.model.dataModels.Pago;
import monk.com.mx.misalleandroid.model.dataModels.Periodo;
import monk.com.mx.misalleandroid.model.dataModels.Usuario;
import monk.com.mx.misalleandroid.presenter.AdvertisingPresenter;
import monk.com.mx.misalleandroid.presenter.LoadingPresenter;

/**
 * Created by edago on 7/2/17.
 */
public class InformationManager {

    private ArrayList<Credito> creditos;

    public InformationManager(){

    }

    public void RequestUserInformation(String pMatricula, String pPassword){

        Usuario user = new Usuario(pMatricula, pPassword);
        ScrapperRequest scrapperRequest = new ScrapperRequest();
        scrapperRequest.GetAlumnoRequest(user);
    }

    public void SaveUserInformation(Alumno alumno) {
        FileHandler fileHandler = new FileHandler();
        fileHandler.CreateFile("usuario", JsonHandler.SerializeObject(alumno.getUsuario()));
        fileHandler.CreateFile("alumnoInfo", JsonHandler.SerializeObject(new AlumnoInfo(alumno)));
        fileHandler.CreateFile("periodos", JsonHandler.SerializeObject(alumno.getPeriodos()));
        fileHandler.CreateFile("horario", JsonHandler.SerializeObject(alumno.getClases()));
        fileHandler.CreateFile("creditos", JsonHandler.SerializeObject(alumno.getCreditos()));
        fileHandler.CreateFile("pagos", JsonHandler.SerializeObject(alumno.getPagos()));
    }

    public void onFinishingRequest() {
        LoadingPresenter.onSuccessfulLoading();
    }

    public ArrayList<Clase> getSchedule() {
        FileHandler fileHandler = new FileHandler();
        return JsonHandler.DeserializeClases(fileHandler.ReadFile("horario"));

    }

    public ArrayList<Credito> getCreditos() {
        FileHandler fileHandler = new FileHandler();
        return JsonHandler.DeserializeCreditos(fileHandler.ReadFile("creditos"));
    }

    public ArrayList<Periodo> getPeriodos() {
        FileHandler fileHandler = new FileHandler();
        return JsonHandler.DeserializePeriodos(fileHandler.ReadFile("periodos"));
    }

    public AlumnoInfo getAlumnoInfo() {
        FileHandler fileHandler = new FileHandler();
        return JsonHandler.DeserializeAlumnoInfo(fileHandler.ReadFile("alumnoInfo"));
    }

    public void RequestAdvertisingInformation(AdvertisingPresenter presenter) {
        Usuario dummyData = new Usuario("a", "a");
        ScrapperRequest scrapperRequest = new ScrapperRequest();
        scrapperRequest.getAdvertisingRequest(dummyData, presenter);
    }

    public void RegisterClick(Click click) {
        ScrapperRequest scrapperRequest = new ScrapperRequest();
        scrapperRequest.setClickRequest(click);
    }

    public String getMatricula() {
        Context context = MyApplication.getContext();
        String preferencesFile = MyApplication.getPreferencesString();
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesFile, context.MODE_PRIVATE);
        return sharedPreferences.getString("matricula", "00000");
    }

    public ArrayList<Pago> getPayments() {
        FileHandler fileHandler = new FileHandler();
        return JsonHandler.DeserializePagos(fileHandler.ReadFile("pagos"));
    }
}
