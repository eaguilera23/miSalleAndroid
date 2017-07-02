package monk.com.mx.misalleandroid.model.dataModels;

/**
 * Created by edago on 7/2/17.
 */
public class PeriodoFaltas {

    //region Fields
    private int year;
    private int mes_inicio;
    private int mes_final;
    //endregion

    //region Encapsulation
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMes_inicio() {
        return mes_inicio;
    }

    public void setMes_inicio(int mes_inicio) {
        this.mes_inicio = mes_inicio;
    }

    public int getMes_final() {
        return mes_final;
    }

    public void setMes_final(int mes_final) {
        this.mes_final = mes_final;
    }
    //endregion

    //region Constructors
    public PeriodoFaltas(){}

    public PeriodoFaltas(int _mes_inicio, int _mes_final, int _year){
        this.mes_inicio = _mes_inicio;
        this.mes_final = _mes_final;
        this.year = _year;
    }
    //endregion
}
