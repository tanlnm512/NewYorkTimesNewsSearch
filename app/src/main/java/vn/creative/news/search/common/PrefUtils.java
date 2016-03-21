package vn.creative.news.search.common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tanlnm on 3/21/2016.
 */
public class PrefUtils {
    private static PrefUtils instance = null;
    private SharedPreferences sharedPref = null;

    public static PrefUtils getInstance(Activity activity) {
        if (instance == null) {
            instance = new PrefUtils(activity);
        }
        return instance;
    }

    private PrefUtils(Activity activity) {
        sharedPref = activity.getPreferences(Context.MODE_APPEND);
    }

    public String readString(String key) {
        try {
            return sharedPref.getString(key, "");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void writeString(String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value).apply();
    }
}
