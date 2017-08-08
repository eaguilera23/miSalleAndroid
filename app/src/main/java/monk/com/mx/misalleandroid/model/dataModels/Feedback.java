package monk.com.mx.misalleandroid.model.dataModels;

/**
 * Created by edago on 8/7/17.
 */

public class Feedback {
    private String matricula;
    private String texto;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Feedback(){

    }

    public Feedback(String matricula, String texto){
        this.matricula = matricula;
        this.texto = texto;
    }
}
