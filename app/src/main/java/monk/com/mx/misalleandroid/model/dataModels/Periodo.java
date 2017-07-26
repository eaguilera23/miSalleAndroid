package monk.com.mx.misalleandroid.model.dataModels;

import java.util.ArrayList;

/**
 * Created by edago on 7/2/17.
 */
public class Periodo {

    //region Fields
    private String nombre;
    private ArrayList<Boleta> boletas;
    //endregion

    //region Encapsulation
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Boleta> getBoletas() {
        return boletas;
    }

    public void setBoletas(ArrayList<Boleta> boletas) {
        this.boletas = boletas;
    }
    //endregion

    //region Constructors
    public Periodo(){}

    public Periodo(String _nombre, ArrayList<Boleta> _boletas){
        this.nombre = _nombre;
        this.boletas = _boletas;
    }


    //endregion
}
