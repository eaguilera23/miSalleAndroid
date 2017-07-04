package monk.com.mx.misalleandroid.domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import monk.com.mx.misalleandroid.model.dataModels.AlumnoInfo;
import monk.com.mx.misalleandroid.model.dataModels.Clase;
import monk.com.mx.misalleandroid.model.dataModels.Credito;
import monk.com.mx.misalleandroid.model.dataModels.Periodo;

/**
 * Created by edago on 7/2/17.
 */
public class JsonHandler {
    static Gson gson = new Gson();

    public static String SerializeObject(Object object) {return gson.toJson(object); }

    public static ArrayList<Clase> DeserializeClases(String json){
        ArrayList<Clase> clases;
        try {
            Type type = new TypeToken<ArrayList<Clase>>(){}.getType();
            clases = gson.fromJson(json, type);
        }
        catch (Exception ex){
            clases = null;
        }
        return clases;
    }

    public static ArrayList<Credito> DeserializeCreditos(String json) {
        ArrayList<Credito> creditos;
        try {
            Type type = new TypeToken<ArrayList<Credito>>(){}.getType();
            creditos = gson.fromJson(json, type);
        }
        catch (Exception ex){
            creditos = null;
        }
        return creditos;
    }

    public static ArrayList<Periodo> DeserializePeriodos(String json) {
        ArrayList<Periodo> periodos;
        try {
            Type type = new TypeToken<ArrayList<Periodo>>(){}.getType();
            periodos = gson.fromJson(json, type);
        }
        catch (Exception ex){
            periodos = null;
        }
        return periodos;
    }

    public static AlumnoInfo DeserializeAlumnoInfo(String json) {
        AlumnoInfo alumnoInfo;
        try {
            Type type = new TypeToken<AlumnoInfo>(){}.getType();
            alumnoInfo = gson.fromJson(json, type);
        }
        catch (Exception ex){
            alumnoInfo = null;
        }
        return alumnoInfo;
    }
}
