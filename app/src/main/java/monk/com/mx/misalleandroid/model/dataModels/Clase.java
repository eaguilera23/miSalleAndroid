package monk.com.mx.misalleandroid.model.dataModels;

/**
 * Created by edago on 7/2/17.
 */
public class Clase {

    //region Fields
    private int hora_inicio;
    private int hora_final;
    private int dia;
    private Materia materia;
    private Profesor profesor;
    //endregion

    //region Encapsulation
    public int getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(int hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public int getHora_final() {
        return hora_final;
    }

    public void setHora_final(int hora_final) {
        this.hora_final = hora_final;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    //endregion

    //region Constructors
    public Clase(){}

    public Clase(int _hora_inicio, int _hora_final, int _dia, Materia _materia, Profesor _profesor){
        this.hora_inicio = _hora_inicio;
        this.hora_final = _hora_final;
        this.dia = _dia;
        this.materia = _materia;
        this.profesor = _profesor;
    }
    //endregion
}
