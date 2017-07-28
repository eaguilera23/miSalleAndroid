package monk.com.mx.misalleandroid;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;

/**
 * Created by edago on 7/2/17.
 */
public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        FirebaseApp.initializeApp(this);
    }
    public static Context getContext() {
        return mContext;
    }

    public static String getPreferencesString() {
        return "mx.com.monk.misalle.PREFERENCE_FILE_KEY2302";
    }
}
