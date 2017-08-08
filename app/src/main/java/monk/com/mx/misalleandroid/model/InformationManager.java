package monk.com.mx.misalleandroid.model;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import monk.com.mx.misalleandroid.MyApplication;
import monk.com.mx.misalleandroid.domain.JsonHandler;
import monk.com.mx.misalleandroid.model.dataModels.Alumno;
import monk.com.mx.misalleandroid.model.dataModels.AlumnoInfo;
import monk.com.mx.misalleandroid.model.dataModels.Anuncio;
import monk.com.mx.misalleandroid.model.dataModels.Clase;
import monk.com.mx.misalleandroid.model.dataModels.Click;
import monk.com.mx.misalleandroid.model.dataModels.Credito;
import monk.com.mx.misalleandroid.model.dataModels.Pago;
import monk.com.mx.misalleandroid.model.dataModels.Periodo;
import monk.com.mx.misalleandroid.model.dataModels.Usuario;
import monk.com.mx.misalleandroid.presenter.AdvertisingPresenter;
import monk.com.mx.misalleandroid.presenter.GradesPresenter;
import monk.com.mx.misalleandroid.presenter.HomePresenter;
import monk.com.mx.misalleandroid.presenter.LoadingPresenter;
import monk.com.mx.misalleandroid.presenter.MainPresenter;

/**
 * Created by edago on 7/2/17.
 */
public class InformationManager {

    public InformationManager(){

    }

    public ArrayList<Clase> getSchedule() {
        FileHandler fileHandler = new FileHandler();
        return JsonHandler.DeserializeClases(fileHandler.ReadFile("horario"));

    }

    public ArrayList<Clase> getScheduleForDay(int day, ArrayList<Clase> schedule){
        //Se
        ArrayList<Clase> scheduleForDay = new ArrayList<>();

        //Obtain classes of the day
        for (Clase clase: schedule) {
            if (clase.getDia() == day)
                scheduleForDay.add(clase);
        }

        //Sort by hour
        Collections.sort(scheduleForDay, new Comparator<Clase>() {
            @Override
            public int compare(Clase lhs, Clase rhs) {
                return lhs.getHora_inicio() - rhs.getHora_inicio();
            }
        });

        return scheduleForDay;
    }

    public ArrayList<Credito> getCreditos() {
        FileHandler fileHandler = new FileHandler();
        return JsonHandler.DeserializeCreditos(fileHandler.ReadFile("creditos"));
    }

    public void setCreditos(ArrayList<Credito> creditos) {
        FileHandler fileHandler = new FileHandler();
        fileHandler.CreateFile("creditos", JsonHandler.SerializeObject(creditos));
    }

    public ArrayList<Periodo> getPeriodos() {
        FileHandler fileHandler = new FileHandler();
        return JsonHandler.DeserializePeriodos(fileHandler.ReadFile("periodos"));
    }

    public void setPeriodos(ArrayList<Periodo> periodos){
        FileHandler fileHandler = new FileHandler();
        fileHandler.CreateFile("periodos", JsonHandler.SerializeObject(periodos));
    }

    public Usuario getUsuario(){
        FileHandler fileHandler = new FileHandler();
        return JsonHandler.DeserializeUsuario(fileHandler.ReadFile("usuario"));
    }

    public AlumnoInfo getAlumnoInfo() {
        FileHandler fileHandler = new FileHandler();
        return JsonHandler.DeserializeAlumnoInfo(fileHandler.ReadFile("alumnoInfo"));
    }

    public String getMatricula() {
        Context context = MyApplication.getContext();
        String preferencesFile = MyApplication.getPreferencesString();
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesFile, context.MODE_PRIVATE);
        return sharedPreferences.getString("matricula", "00000");
    }

    public ArrayList<Pago> getPayments() {
        FileHandler fileHandler = new FileHandler();
        return JsonHandler.DeserializePagos(fileHandler.ReadFile("pagos"));
    }

    public Bitmap getProfilePicture() {
        Bitmap bitmap = null;
        Context context = MyApplication.getContext();
        String preferencesFile = MyApplication.getPreferencesString();
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesFile, context.MODE_PRIVATE);
        String path = sharedPreferences.getString("img_perfil", null);
        if (path != null){
            File file = new File(path, "profile.jpg");
            try {
                bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public void setProfilePicture(Bitmap picture, final MainPresenter mainPresenter) {
        ContextWrapper contextWrapper = new ContextWrapper(MyApplication.getContext());
        File directory = contextWrapper.getDir("img", Context.MODE_PRIVATE);
        File mypath = new File(directory, "profile.jpg");
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(mypath);
            picture.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Context context = MyApplication.getContext();
        String preferencesFile = MyApplication.getPreferencesString();
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesFile, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("img_perfil", directory.getAbsolutePath());
        editor.commit();
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        StorageReference imageReference = storageReference.child(getMatricula() + "/profile.jpg");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        picture.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask task = imageReference.putBytes(data);
        task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri profile_url = taskSnapshot.getDownloadUrl();
                mainPresenter.setProfilePicture(profile_url);
            }
        });
    }

    public void DeleteProfilePicture() {
        Context context = MyApplication.getContext();
        String preferencesFile = MyApplication.getPreferencesString();
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesFile, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("img_perfil", null);
        editor.commit();
    }

    public void setUserInformation(Alumno alumno) {
        FileHandler fileHandler = new FileHandler();
        fileHandler.CreateFile("usuario", JsonHandler.SerializeObject(alumno.getUsuario()));
        fileHandler.CreateFile("alumnoInfo", JsonHandler.SerializeObject(new AlumnoInfo(alumno)));
        fileHandler.CreateFile("periodos", JsonHandler.SerializeObject(alumno.getPeriodos()));
        fileHandler.CreateFile("horario", JsonHandler.SerializeObject(alumno.getClases()));
        fileHandler.CreateFile("creditos", JsonHandler.SerializeObject(alumno.getCreditos()));
        fileHandler.CreateFile("pagos", JsonHandler.SerializeObject(alumno.getPagos()));
    }

    public void setMatriculaLocal(String matricula){
        Context context = MyApplication.getContext();
        String preferencesFile = MyApplication.getPreferencesString();
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesFile, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("matricula", matricula);
        editor.commit();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void setLastFeedbackDate(int date) {
        Context context = MyApplication.getContext();
        String preferencesFile = MyApplication.getPreferencesString();
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesFile, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("lastFeedback", date);
        editor.commit();
    }

    public int getLastFeedbackDate() {
        Context context = MyApplication.getContext();
        String preferencesFile = MyApplication.getPreferencesString();
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesFile, context.MODE_PRIVATE);
        return sharedPreferences.getInt("lastFeedback", 0);
    }
}
