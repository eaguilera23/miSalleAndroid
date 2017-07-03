package monk.com.mx.misalleandroid.presenter;

import java.io.IOException;
import java.util.ArrayList;

import monk.com.mx.misalleandroid.domain.DataHelper;
import monk.com.mx.misalleandroid.model.InformationManager;
import monk.com.mx.misalleandroid.model.dataModels.Clase;
import monk.com.mx.misalleandroid.model.dataModels.Credito;
import monk.com.mx.misalleandroid.view.HomeFragment;

/**
 * Created by edago on 7/2/17.
 */
public class HomePresenter {
    private HomeFragment homeFragment;
    private InformationManager informationManager;

    public HomePresenter(HomeFragment homefrag){
        this.homeFragment = homefrag;
        informationManager = new InformationManager();
        setNextClass();
        setCreditos();
    }

    private void setNextClass(){
        Clase clase = DataHelper.getNextClass(informationManager.getSchedule());
        homeFragment.setNextClass(clase);
    }

    private void setCreditos() {
        ArrayList<Credito> creditos = informationManager.getCreditos();
        homeFragment.setCreditos(creditos);
    }
}
