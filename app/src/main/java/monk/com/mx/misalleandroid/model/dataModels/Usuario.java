package monk.com.mx.misalleandroid.model.dataModels;

/**
 * Created by edago on 7/2/17.
 */
public class Usuario {

    //region Fields
    private String matricula;
    private String password;
    //endregion

    //region Encapsulation
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //endregion

    //region Constructors
    public Usuario(){}

    public Usuario(String _matricula, String _password){
        this.matricula = _matricula;
        this.password = _password;
    }
    //endregion
}
