package monk.com.mx.misalleandroid.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import monk.com.mx.misalleandroid.model.InformationManager;
import monk.com.mx.misalleandroid.model.dataModels.Anuncio;
import monk.com.mx.misalleandroid.model.dataModels.Click;
import monk.com.mx.misalleandroid.view.AdvertisingFragment;

/**
 * Created by edago on 7/13/17.
 */
public class AdvertisingPresenter {

    AdvertisingFragment advertisingFragment;
    InformationManager informationManager;
    Anuncio anuncio;

    public AdvertisingPresenter(AdvertisingFragment fragment){
        advertisingFragment = fragment;
        getAdvertisingImage();
    }

    private void getAdvertisingImage(){
        informationManager = new InformationManager();
        informationManager.RequestAdvertisingInformation(this);
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

        informationManager.RegisterClick(click);
    }
}
