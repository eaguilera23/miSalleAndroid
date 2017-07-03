package monk.com.mx.misalleandroid.model;

import monk.com.mx.misalleandroid.model.dataModels.Alumno;
import monk.com.mx.misalleandroid.model.dataModels.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by edago on 7/2/17.
 */
public class ScrapperRequest {

    private String BASE_URL = "http://pruebatarea2302.herokuapp.com/";

    public ScrapperRequest(){

    }

    public void GetAlumnoRequest(Usuario user) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ScrapperService scrapperService = retrofit.create(ScrapperService.class);
        final Call<Alumno> alumnoCall = scrapperService.getAlumnoInfo(user);
        alumnoCall.enqueue(new Callback<Alumno>() {
            @Override
            public void onResponse(Call<Alumno> call, Response<Alumno> response) {
                if (response.isSuccessful()){
                    Alumno alumnoResponse = response.body();
                    InformationManager informationManager = new InformationManager();
                    informationManager.SaveUserInformation(alumnoResponse);
                    informationManager.onFinishingRequest();
                }
            }

            @Override
            public void onFailure(Call<Alumno> call, Throwable t) {

            }
        });
    }
}
