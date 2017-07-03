package monk.com.mx.misalleandroid.model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import monk.com.mx.misalleandroid.MyApplication;

/**
 * Created by edago on 7/2/17.
 */
public class FileHandler {

    FileInputStream file = null;
    InputStreamReader inputStreamReader = null;
    BufferedReader bufferedReader = null;
    StringBuilder stringBuilder = null;
    public FileHandler(){
    }

    public void CreateFile(String fileName, String content) {
        FileOutputStream outputStream;
        try {
            //Create file in internal storage
            outputStream = MyApplication.getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public String ReadFile(String fileName){
        String json = null;
        try{
            json = GetData(fileName).toString();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  json;
    }

    protected StringBuilder GetData(String fileName) throws IOException {
        try {
            file = MyApplication.getContext().openFileInput(fileName);
            inputStreamReader = new InputStreamReader(file);
            bufferedReader = new BufferedReader(inputStreamReader);
            stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }catch (Exception ex){
            return null;
        }finally {
            file.close();
            inputStreamReader.close();
            bufferedReader.close();
        }

        return stringBuilder;
    }
}
