package monk.com.mx.misalleandroid.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

import monk.com.mx.misalleandroid.domain.StringFormater;
import monk.com.mx.misalleandroid.model.InformationManager;
import monk.com.mx.misalleandroid.model.dataModels.AlumnoInfo;
import monk.com.mx.misalleandroid.view.MainActivity;

/**
 * Created by edago on 7/2/17.
 */
public class MainPresenter {

    private MainActivity mainActivity;
    private InformationManager informationManager;
    private FirebaseAuth firebaseAuth;
    private final int PICTURE_TAKEN_FROM_CAMERA = 1;
    private final int PICTURE_TAKEN_FROM_GALLERY = 2;

    public MainPresenter(MainActivity pActivity) {
        mainActivity = pActivity;
        informationManager = new InformationManager();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public AlumnoInfo getAlumnoInfo() {
        AlumnoInfo alumnoInfo = informationManager.getAlumnoInfo();
        return alumnoInfo;
    }

    public String getCompleteName(AlumnoInfo alumnoInfo) {
        return StringFormater.GetCompleteName(alumnoInfo.getNombre(), alumnoInfo.getApellido_p(), alumnoInfo.getApellido_m());
    }

    public void setAlumnoInfo() {
        mainActivity.setAlumnoInfo(getAlumnoInfo());
    }


    public void getPhoto(int requestCode, int resultCode, Intent data) throws IOException {
        Bitmap picture = null;
        Uri uri;
        switch (requestCode){
            case PICTURE_TAKEN_FROM_CAMERA:
                if (resultCode == mainActivity.RESULT_OK) {
                    Bundle extras = data.getExtras();
                    picture = (Bitmap) extras.get("data");
                }else{
                    return;
                }
                break;
            case PICTURE_TAKEN_FROM_GALLERY:
                if (resultCode == mainActivity.RESULT_OK){
                    uri = data.getData();
                    picture = MediaStore.Images.Media.getBitmap(mainActivity.getContentResolver(), uri);
                }else{
                    return;
                }
                break;
            default:
                return;
        }
        informationManager.setProfilePicture(picture);
        setProfilePicture(picture);
    }

    private void setProfilePicture(Bitmap picture) {
        mainActivity.setProfilePicture(picture);
    }

    public void setProfilePicture() {
        Bitmap picture = informationManager.getProfilePicture();
        if (picture != null)
            setProfilePicture(picture);
    }

    public void Logout() {
        informationManager.setSession(false);
        informationManager.DeleteProfilePicture();
        firebaseAuth.signOut();
    }
}
