package monk.com.mx.misalleandroid.domain;

import android.content.Context;
import android.graphics.Typeface;

import monk.com.mx.misalleandroid.MyApplication;

/**
 * Created by edago on 7/24/17.
 */
public class FontManager {
    public static final String ROOT = "fonts/",
            FONTAWESOME = ROOT + "fontawesome-webfont.ttf";

    public static Typeface getTypeface(String font) {
        Context context = MyApplication.getContext();
        return Typeface.createFromAsset(context.getAssets(), font);
    }
}
