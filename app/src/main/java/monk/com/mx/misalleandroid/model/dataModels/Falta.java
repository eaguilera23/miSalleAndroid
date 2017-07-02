package monk.com.mx.misalleandroid.model.dataModels;

/**
 * Created by edago on 7/2/17.
 */
public class Falta {

    //region Fields
    private int cantidad;
    private PeriodoFaltas periodo;
    private Materia materia;
    //endregion

    //region Encapsulation
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public PeriodoFaltas getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoFaltas periodo) {
        this.periodo = periodo;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    //endregion

    //region Constructors
    public Falta(){}

    public Falta(int _cantidad, PeriodoFaltas _periodo, Materia _materia){
        this.cantidad = _cantidad;
        this.periodo = _periodo;
        this.materia = _materia;
    }
    //endregion
}
