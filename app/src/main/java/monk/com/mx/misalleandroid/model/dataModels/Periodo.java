package monk.com.mx.misalleandroid.model.dataModels;

import java.util.ArrayList;

/**
 * Created by edago on 7/2/17.
 */
public class Periodo {

    //region Fields
    private int mes_inicio;
    private int mes_final;
    private int year;
    private ArrayList<Boleta> boletas;
    //endregion

    //region Encapsulation
    public int getMes_inicio() {
        return mes_inicio;
    }

    public void setMes_inicio(int mes_inicio) {
        this.mes_inicio = mes_inicio;
    }

    public int getMes_final() {
        return mes_final;
    }

    public void setMes_final(int mes_final) {
        this.mes_final = mes_final;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public Periodo(int _mes_inicio, int _mes_final, int _year, ArrayList<Boleta> _boletas){
        this.mes_inicio = _mes_inicio;
        this.mes_final = _mes_final;
        this.year = _year;
        this.boletas = _boletas;
    }
    //endregion
}
