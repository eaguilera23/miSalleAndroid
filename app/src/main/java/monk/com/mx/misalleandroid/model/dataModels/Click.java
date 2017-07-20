package monk.com.mx.misalleandroid.model.dataModels;

/**
 * Created by edago on 7/18/17.
 */
public class Click {

    private Integer campaign_id;
    private String matricula;

    public Click(String matricula, Integer campaign_id) {
        this.matricula = matricula;
        this.campaign_id = campaign_id;
    }

    public Click() {
    }

    public Integer getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(Integer campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
