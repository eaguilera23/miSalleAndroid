package monk.com.mx.misalleandroid.model.dataModels;

import android.content.Intent;

/**
 * Created by edago on 7/13/17.
 */
public class Anuncio {
    private Integer campaign_id;
    private String ruta_imagen;
    private String destino_click;

    public Integer getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(Integer campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }

    public String getDestino_click() {
        return destino_click;
    }

    public void setDestino_click(String destino_click) {
        this.destino_click = destino_click;
    }

    public Anuncio(){

    }

    public Anuncio(Integer pCampaign_id, String pRuta_imagen, String pDestino_click){
        this.campaign_id = pCampaign_id;
        this.ruta_imagen = pRuta_imagen;
        this.destino_click = pDestino_click;
    }
}
