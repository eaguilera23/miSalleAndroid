package monk.com.mx.misalleandroid.model.dataModels;

import java.util.ArrayList;

/**
 * Created by edago on 7/24/17.
 */
public class PeriodosResult {
    private ArrayList<Periodo> periodos;

    public PeriodosResult(){

    }

    public PeriodosResult(ArrayList<Periodo> periodos) {
        this.periodos = periodos;
    }


    public ArrayList<Periodo> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(ArrayList<Periodo> periodos) {
        this.periodos = periodos;
    }
}
