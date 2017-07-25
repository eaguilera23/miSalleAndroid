package monk.com.mx.misalleandroid.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import monk.com.mx.misalleandroid.model.InformationManager;
import monk.com.mx.misalleandroid.model.ScrapperRequest;
import monk.com.mx.misalleandroid.model.dataModels.Anuncio;
import monk.com.mx.misalleandroid.model.dataModels.Click;
import monk.com.mx.misalleandroid.model.dataModels.Usuario;
import monk.com.mx.misalleandroid.view.AdvertisingFragment;

/**
 * Created by edago on 7/13/17.
 */
public class AdvertisingPresenter {

    AdvertisingFragment advertisingFragment;
    InformationManager informationManager;
    ScrapperRequest scrapperRequest;
    Anuncio anuncio;

    public AdvertisingPresenter(AdvertisingFragment fragment){
        advertisingFragment = fragment;
        scrapperRequest = new ScrapperRequest();
        informationManager = new InformationManager();
        getAdvertisingImage();
    }

    private void getAdvertisingImage(){
        Usuario dummyData = new Usuario("a", "a");
        scrapperRequest.getAdvertisingRequest(dummyData, this);
    }

    public void setAdvertisingImage(Anuncio ad){
        anuncio = ad;
        advertisingFragment.setImgAdvertising(ad);
    }

    public void RegisterClick() {
        String matricula = informationManager.getMatricula();

        Click click = new Click();
        click.setCampaign_id(anuncio.getCampaign_id());
        click.setMatricula(matricula);

        scrapperRequest.setClickRequest(click);
    }
}
