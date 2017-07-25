package monk.com.mx.misalleandroid.model;

import java.util.List;

import monk.com.mx.misalleandroid.model.dataModels.Alumno;
import monk.com.mx.misalleandroid.model.dataModels.Anuncio;
import monk.com.mx.misalleandroid.model.dataModels.Click;
import monk.com.mx.misalleandroid.model.dataModels.CreditosResult;
import monk.com.mx.misalleandroid.model.dataModels.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by edago on 7/2/17.
 */
public interface ScrapperService {

    //Calling service for Alumno info, returns Alumno class
    @POST("alumno")
    Call<Alumno> getAlumnoInfo(@Body Usuario usuario);

    @POST("anuncio")
    Call<Anuncio>getAdvertisingInfo(@Body Usuario usuario);

    @POST("click")
    Call<Anuncio> setClickOnAdvertising(@Body Click click);

    @POST("creditos")
    Call<CreditosResult> getCreditos(@Body Usuario usuario);
}
