package monk.com.mx.misalleandroid.model.dataModels;

import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by edago on 7/2/17.
 */
public class Boleta {

    //region Fields
    private String tipo;
    private Materia materia;
    private Profesor profesor;
    private Integer faltas;
    private ArrayList<Parcial> parciales;
    //endregion


    //region Encapsulation
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public ArrayList<Parcial> getParciales() {
        return parciales;
    }

    public void setParciales(ArrayList<Parcial> parciales) {
        this.parciales = parciales;
    }

    public Integer getFaltas() { return faltas; }

    public void setFaltas(Integer faltas) { this.faltas = faltas; }
    //endregion

    //region Constructors
    public Boleta(){}

    public Boleta(String _tipo, Materia _materia, Profesor _profesor, ArrayList<Parcial> _parciales){
        this.tipo = _tipo;
        this.materia = _materia;
        this.profesor = _profesor;
        this.parciales = _parciales;
    }
    //endregion
}
