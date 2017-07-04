package monk.com.mx.misalleandroid.presenter;

import java.util.ArrayList;

import monk.com.mx.misalleandroid.domain.StringFormater;
import monk.com.mx.misalleandroid.model.InformationManager;
import monk.com.mx.misalleandroid.model.dataModels.Boleta;
import monk.com.mx.misalleandroid.model.dataModels.Periodo;
import monk.com.mx.misalleandroid.view.GradesFragment;

/**
 * Created by edago on 7/3/17.
 */
public class GradesPresenter {

    private GradesFragment gradesFragment;

    public GradesPresenter(GradesFragment fragment){
        gradesFragment = fragment;
    }
    public GradesPresenter(){

    }

    public ArrayList<String> getPeriodosName(String[] months) {
        ArrayList<Periodo> periods = getPeriodos();
        ArrayList<String> periods_name = new ArrayList<>();

        for (int i = 0; i < periods.size(); i++){
            Periodo periodo = periods.get(i);
            periods_name.add(StringFormater.ToPeriod(periodo.getMes_inicio(), periodo.getMes_final(), months, periodo.getYear()));
        }

        return periods_name;
    }

    public ArrayList<Periodo> getPeriodos(){
        InformationManager informationManager = new InformationManager();
        return informationManager.getPeriodos();
    }

    public ArrayList<Boleta> getGrades(int period) {
        ArrayList<Periodo> periodos = getPeriodos();
        return periodos.get(period).getBoletas();
    }
}
