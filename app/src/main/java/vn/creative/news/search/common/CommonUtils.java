package vn.creative.news.search.common;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import vn.creative.news.search.model.SearchSettingModel;

/**
 * Created by TanLe on 3/19/16.
 */
public class CommonUtils {
    public static Boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private static final String URL = "http://api.nytimes.com/svc/search/v2/articlesearch.json?q=%1$s&page=%2$s&api-key=f5571f6befeabe596b4c7979f49b9684:18:74749744";
    private static SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyyMMdd", Locale.US);

    public static String getSearchLink(Activity activity, String query, int page) {
        try {
            String result;
            query = URLEncoder.encode(query, "UTF-8");
            result = String.format(URL, query, page);

            SearchSettingModel searchSetting = new Gson().fromJson(PrefUtils.getInstance(activity).readString("setting"), SearchSettingModel.class);
            if (searchSetting != null) {
                if (searchSetting.getBeginTime() > 0) {
                    result += "&begin_date=" + yyyymmdd.format(new Date(searchSetting.getBeginTime()));
                }

                if (!TextUtils.isEmpty(searchSetting.getSortOrder())) {
                    result += "&sort=" + searchSetting.getSortOrder();
                }

                String ext = "";
                if (searchSetting.isFashion()) {
                    ext += "\"Fashion\"";
                }

                if (searchSetting.isNaturalWorld()) {
                    ext += "\"The natural world\"";
                }

                if (searchSetting.isTech()) {
                    ext += "\"Technology\"";
                }

                if (!TextUtils.isEmpty(ext)) {
                    result += String.format("&fq=news_desk:(%1$s)", ext);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
