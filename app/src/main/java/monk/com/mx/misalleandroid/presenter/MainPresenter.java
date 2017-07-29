package monk.com.mx.misalleandroid.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

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
        informationManager.setProfilePicture(picture, this);
    }

    public void setProfilePicture(Uri picture) {
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                .setPhotoUri(picture)
                .build();
        firebaseUser.updateProfile(userProfileChangeRequest)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        mainActivity.setProfilePicture(user.getPhotoUrl());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mainActivity.onErrorProfilePictureUpdate();
                    }
                });

    }

    public void setProfilePicture() {
        if (informationManager.isNetworkAvailable()) {
            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null && user.getPhotoUrl() != null) {
                mainActivity.setProfilePicture(user.getPhotoUrl());
            }
        }else{
            Bitmap profile_pic = informationManager.getProfilePicture();
            mainActivity.setProfilePicture(profile_pic);
        }
    }

    public void Logout() {
        informationManager.DeleteProfilePicture();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
    }
}
