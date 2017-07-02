package monk.com.mx.misalleandroid.model.dataModels;

/**
 * Created by edago on 7/2/17.
 */
public class Materia {

    //region Fields
    private String nombre;
    private int color;
    //endregion

    //region Encapsulation
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    //endregion

    //region Constructors
    public Materia(){}

    public Materia(String _nombre){
        this.nombre = _nombre;
    }
    //endregion
}
