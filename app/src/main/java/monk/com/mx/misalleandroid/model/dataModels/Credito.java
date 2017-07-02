package monk.com.mx.misalleandroid.model.dataModels;

/**
 * Created by edago on 7/2/17.
 */
public class Credito {

    //region Fields
    private String tipo;
    private int necesarios;
    private int actuales;
    //endregion

    //region Encapsulation
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNecesarios() {
        return necesarios;
    }

    public void setNecesarios(int necesarios) {
        this.necesarios = necesarios;
    }

    public int getActuales() {
        return actuales;
    }

    public void setActuales(int actuales) {
        this.actuales = actuales;
    }
    //endregion

    //region Constructors
    public Credito(){}

    public Credito(String _tipo, int _necesarios, int _actuales){
        this.tipo = _tipo;
        this.actuales = _actuales;
        this.necesarios = _necesarios;
    }
    //endregion
}
