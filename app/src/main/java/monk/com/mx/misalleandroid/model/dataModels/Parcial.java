package monk.com.mx.misalleandroid.model.dataModels;

/**
 * Created by edago on 7/2/17.
 */
public class Parcial {

    //region Fields
    private  int numero;
    private float calificacion;
    //endregion

    //region Encapsulation
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }
    //endregion

    //region Constructor
    public Parcial(){}

    public Parcial(int _numero, float _calificacion){
        this.calificacion = _calificacion;
        this.numero = _numero;
    }
    //endregion
}
