package monk.com.mx.misalleandroid.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import monk.com.mx.misalleandroid.MyApplication;
import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.model.dataModels.Anuncio;
import monk.com.mx.misalleandroid.presenter.AdvertisingPresenter;

/**
 * Created by edago on 7/13/17.
 */
public class AdvertisingFragment extends Fragment {

    AdvertisingPresenter advertisingPresenter;
    Anuncio anuncio;

    public AdvertisingFragment(){

    }

    ImageView _img_advertising;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_advertising_main, container, false);

        _img_advertising = (ImageView)v.findViewById(R.id.img_advertising);

        advertisingPresenter = new AdvertisingPresenter(this);

        return v;
    }

    public void setImgAdvertising(final Anuncio ad){
        anuncio = ad;
        Picasso.with(MyApplication.getContext()).load(ad.getRuta_imagen()).into(_img_advertising);
        _img_advertising.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ad.getDestino_click()));
                startActivity(browserIntent);
            }
        });
    }

}
