package monk.com.mx.misalleandroid.model.dataModels;

/**
 * Created by edago on 7/2/17.
 */
public class Campus {

    //region Fields
    private String nombre;
    //endregion

    //region Encapsulation
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //endregion

    //region Constructors
    public Campus(){}

    public Campus(String _nombre){
        this.nombre = _nombre;
    }
    //endregion
}
