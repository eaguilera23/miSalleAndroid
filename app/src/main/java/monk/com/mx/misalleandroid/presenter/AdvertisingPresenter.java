package monk.com.mx.misalleandroid.presenter;

import monk.com.mx.misalleandroid.model.InformationManager;
import monk.com.mx.misalleandroid.model.dataModels.Anuncio;
import monk.com.mx.misalleandroid.view.AdvertisingFragment;

/**
 * Created by edago on 7/13/17.
 */
public class AdvertisingPresenter {

    AdvertisingFragment advertisingFragment;
    InformationManager informationManager;

    public AdvertisingPresenter(AdvertisingFragment fragment){
        advertisingFragment = fragment;
        getAdvertisingImage();
    }

    private void getAdvertisingImage(){
        informationManager = new InformationManager();
        informationManager.RequestAdvertisingInformation(this);
    }

    public void setAdvertisingImage(Anuncio anuncio){
        advertisingFragment.setImgAdvertising(anuncio);
    }
}
