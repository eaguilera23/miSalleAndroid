package monk.com.mx.misalleandroid.presenter;

import monk.com.mx.misalleandroid.domain.StringFormater;
import monk.com.mx.misalleandroid.model.InformationManager;
import monk.com.mx.misalleandroid.model.dataModels.AlumnoInfo;
import monk.com.mx.misalleandroid.view.MainActivity;

/**
 * Created by edago on 7/2/17.
 */
public class MainPresenter {

    private MainActivity mainActivity;

    public MainPresenter(MainActivity pActivity) {
        mainActivity = pActivity;
    }

    public AlumnoInfo getAlumnoInfo() {
        InformationManager informationManager = new InformationManager();
        AlumnoInfo alumnoInfo = informationManager.getAlumnoInfo();
        return alumnoInfo;
    }

    public String getCompleteName(AlumnoInfo alumnoInfo) {
        return StringFormater.GetCompleteName(alumnoInfo.getNombre(), alumnoInfo.getApellido_p(), alumnoInfo.getApellido_m());
    }

    public void setAlumnoInfo() {
        mainActivity.setAlumnoInfo(getAlumnoInfo());
    }
}
