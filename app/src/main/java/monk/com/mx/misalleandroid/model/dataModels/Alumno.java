package monk.com.mx.misalleandroid.model.dataModels;

import java.util.ArrayList;

/**
 * Created by edago on 7/2/17.
 */
public class Alumno {
    //region Fields
    private String matricula;
    private String nombre;
    private String apellido_m;
    private String apellido_p;
    private String email;
    private Usuario usuario;
    private Campus campus;
    private Programa programa;
    private ArrayList<Credito> creditos;
    private ArrayList<Clase> clases;
    private ArrayList<Periodo> periodos;
    private ArrayList<Falta> faltas;
    private ArrayList<Pago> pagos;
    private Integer nuevo_ingreso;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public ArrayList<Credito> getCreditos() {
        return creditos;
    }

    public void setCreditos(ArrayList<Credito> creditos) {
        this.creditos = creditos;
    }

    public ArrayList<Clase> getClases() {
        return clases;
    }

    public void setClases(ArrayList<Clase> clases) {
        this.clases = clases;
    }

    public ArrayList<Periodo> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(ArrayList<Periodo> periodos) {
        this.periodos = periodos;
    }

    public ArrayList<Falta> getFaltas() {
        return faltas;
    }

    public void setFaltas(ArrayList<Falta> faltas) {
        this.faltas = faltas;
    }

    public ArrayList<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(ArrayList<Pago> pagos) {
        this.pagos = pagos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNuevo_ingreso() {
        return nuevo_ingreso;
    }

    public void setNuevo_ingreso(Integer nuevo_ingreso) {
        this.nuevo_ingreso = nuevo_ingreso;
    }
    //endregion

    //region Constructors
    public Alumno(){}

    public Alumno(String _matricula, String _nombre, String _apellido_p, String _apellido_m,
                  Campus _campus, Programa _programa, Usuario _usuario,ArrayList<Credito> _creditos, ArrayList<Clase> _clases,
                  ArrayList<Periodo> _periodos, ArrayList<Falta> _faltas){
        this.matricula = _matricula;
        this.nombre = _nombre;
        this.apellido_p = _apellido_p;
        this.apellido_m = _apellido_m;
        this.campus = _campus;
        this.programa = _programa;
        this.usuario = _usuario;
        this.creditos = _creditos;
        this.clases = _clases;
        this.periodos = _periodos;
        this.faltas = _faltas;
    }


    //endregion
}
