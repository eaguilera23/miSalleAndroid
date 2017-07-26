package monk.com.mx.misalleandroid.model;

import monk.com.mx.misalleandroid.model.dataModels.Alumno;
import monk.com.mx.misalleandroid.model.dataModels.Anuncio;
import monk.com.mx.misalleandroid.model.dataModels.Click;
import monk.com.mx.misalleandroid.model.dataModels.CreditosResult;
import monk.com.mx.misalleandroid.model.dataModels.PeriodosResult;
import monk.com.mx.misalleandroid.model.dataModels.ScrapperService;
import monk.com.mx.misalleandroid.model.dataModels.Usuario;
import monk.com.mx.misalleandroid.presenter.AdvertisingPresenter;
import monk.com.mx.misalleandroid.presenter.GradesPresenter;
import monk.com.mx.misalleandroid.presenter.HomePresenter;
import monk.com.mx.misalleandroid.presenter.LoadingPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by edago on 7/2/17.
 */
public class ScrapperRequest {

    private String BASE_URL = "http://pruebatarea2302.herokuapp.com/api/v1/";

    public ScrapperRequest(){

    }

    public void GetAlumnoRequest(Usuario user, final LoadingPresenter presenter) {

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
                    presenter.onRequestResponse(alumnoResponse);
                }else {
                    presenter.onErrorLoading("La comunicación con La Salle no pudo ser completada.\nInténtalo más tarde");
                }
            }

            @Override
            public void onFailure(Call<Alumno> call, Throwable t) {
                presenter.onErrorLoading("La comunicación con La Salle no pudo ser completada.\nInténtalo más tarde");
            }
        });
    }

    public void getAdvertisingRequest(Usuario dummyData, final AdvertisingPresenter presenter) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ScrapperService scrapperService = retrofit.create(ScrapperService.class);
        final Call<Anuncio> anuncioCall = scrapperService.getAdvertisingInfo(dummyData);
        anuncioCall.enqueue(new Callback<Anuncio>() {
            @Override
            public void onResponse(Call<Anuncio> call, Response<Anuncio> response) {
                if (response.isSuccessful()){
                    Anuncio anuncioResponse = response.body();
                    presenter.setAdvertisingImage(anuncioResponse);
                }
            }

            @Override
            public void onFailure(Call<Anuncio> call, Throwable t) {

            }
        });
    }

    public void setClickRequest(Click click) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ScrapperService scrapperService = retrofit.create(ScrapperService.class);
        final Call<Anuncio> anuncioCall = scrapperService.setClickOnAdvertising(click);
        anuncioCall.enqueue(new Callback<Anuncio>() {
            @Override
            public void onResponse(Call<Anuncio> call, Response<Anuncio> response) {
                if (response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<Anuncio> call, Throwable t) {

            }
        });
    }

    public void getCreditosRequest(Usuario user, final HomePresenter presenter) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ScrapperService scrapperService = retrofit.create(ScrapperService.class);
        final Call<CreditosResult> creditosResultCallCall = scrapperService.getCreditos(user);
        creditosResultCallCall.enqueue(new Callback<CreditosResult>() {
            @Override
            public void onResponse(Call<CreditosResult> call, Response<CreditosResult> response) {
                if (response.isSuccessful()) {
                    CreditosResult result = response.body();
                    InformationManager informationManager = new InformationManager();
                    informationManager.setCreditos(result.getCreditos());
                }
                presenter.setCreditos();
            }

            @Override
            public void onFailure(Call<CreditosResult> call, Throwable t) {
                presenter.setCreditos();
            }
        });

    }

    public void getPeriodosRequest(Usuario user, final GradesPresenter presenter){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ScrapperService scrapperService = retrofit.create(ScrapperService.class);
        final Call<PeriodosResult> periodosResultCall = scrapperService.getPeriodos(user);
        periodosResultCall.enqueue(new Callback<PeriodosResult>() {
            @Override
            public void onResponse(Call<PeriodosResult> call, Response<PeriodosResult> response) {
                if (response.isSuccessful()) {
                    PeriodosResult result = response.body();
                    InformationManager informationManager = new InformationManager();
                    informationManager.setPeriodos(result.getPeriodos());
                    presenter.setPeriodos();
                }else{
                    presenter.OnError();
                }
            }

            @Override
            public void onFailure(Call<PeriodosResult> call, Throwable t) {
                presenter.OnError();
            }
        });
    }
}
