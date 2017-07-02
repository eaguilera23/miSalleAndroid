package monk.com.mx.misalleandroid.model.dataModels;

/**
 * Created by edago on 7/2/17.
 */
public class AlumnoInfo {

    //region Fields
    private String matricula;
    private String nombre;
    private String apellido_m;
    private String apellido_p;
    private Campus campus;
    private Programa programa;
    //endregion

    //region Encapsulation
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_m() {
        return apellido_m;
    }

    public void setApellido_m(String apellido_m) {
        this.apellido_m = apellido_m;
    }

    public String getApellido_p() {
        return apellido_p;
    }

    public void setApellido_p(String apellido_p) {
        this.apellido_p = apellido_p;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }
    //endregion


    //region Constructors
    public AlumnoInfo(){}

    public AlumnoInfo(String _matricula, String _nombre, String _apellido_p, String _apellido_m,
                      Campus _campus, Programa _programa){
        this.matricula = _matricula;
        this.nombre = _nombre;
        this.apellido_p = _apellido_p;
        this.apellido_m = _apellido_m;
        this.campus = _campus;
        this.programa = _programa;
    }
    //endregion
}
