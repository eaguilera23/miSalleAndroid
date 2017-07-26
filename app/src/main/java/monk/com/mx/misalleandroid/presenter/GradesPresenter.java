package monk.com.mx.misalleandroid.presenter;

import java.util.ArrayList;

import monk.com.mx.misalleandroid.domain.StringFormater;
import monk.com.mx.misalleandroid.model.InformationManager;
import monk.com.mx.misalleandroid.model.ScrapperRequest;
import monk.com.mx.misalleandroid.model.dataModels.Boleta;
import monk.com.mx.misalleandroid.model.dataModels.Periodo;
import monk.com.mx.misalleandroid.model.dataModels.Usuario;
import monk.com.mx.misalleandroid.view.GradesFragment;

/**
 * Created by edago on 7/3/17.
 */
public class GradesPresenter {

    private GradesFragment gradesFragment;
    private InformationManager informationManager;
    private ScrapperRequest scrapperRequest;

    public GradesPresenter(GradesFragment fragment){

        gradesFragment = fragment;
        informationManager = new InformationManager();
    }
    public GradesPresenter(){
        informationManager = new InformationManager();
    }

    public ArrayList<String> getPeriodosName(String[] months) {
        ArrayList<Periodo> periods = getPeriodos();
        ArrayList<String> periods_name = new ArrayList<>();

        for (int i = 0; i < periods.size(); i++){
            Periodo periodo = periods.get(i);
            periods_name.add(periodo.getNombre());
        }

        return periods_name;
    }

    public ArrayList<Periodo> getPeriodos(){
        return informationManager.getPeriodos();
    }

    public ArrayList<Boleta> getGrades(int period) {
        ArrayList<Periodo> periodos = getPeriodos();
        return periodos.get(period).getBoletas();
    }

    public void setPeriodos(){
        gradesFragment.UpdatePeriodsView();
    }

    public void UpdatePeriodos(){
        Usuario user = informationManager.getUsuario();
        scrapperRequest = new ScrapperRequest();
        scrapperRequest.getPeriodosRequest(user, this);
    }

    public void OnError() {
        gradesFragment.OnRefreshError();
    }
}
