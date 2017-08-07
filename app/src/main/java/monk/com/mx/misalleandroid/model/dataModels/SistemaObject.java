package monk.com.mx.misalleandroid.model.dataModels;

/**
 * Created by edago on 8/6/17.
 */

public class SistemaObject {
    private Integer sistema;
    private String texto;

    public Integer getSistema() {
        return sistema;
    }

    public void setSistema(Integer sistema) {
        this.sistema = sistema;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public SistemaObject(){

    }

    public SistemaObject(Integer sistema, String texto){
        this.sistema = sistema;
        this.texto = texto;
    }

    public SistemaObject(String texto){
        this.texto = texto;
    }
}
