package monk.com.mx.misalleandroid.model.dataModels;

/**
 * Created by edago on 8/7/17.
 */

public class Feedback {
    private String usuario_matricula;
    private String texto;

    public String getUsuario_matricula() {
        return usuario_matricula;
    }

    public void setUsuario_matricula(String usuario_matricula) {
        this.usuario_matricula = usuario_matricula;
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
        this.usuario_matricula = matricula;
        this.texto = texto;
    }
}
