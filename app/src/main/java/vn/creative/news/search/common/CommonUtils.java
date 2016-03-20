package vn.creative.news.search.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by TanLe on 3/19/16.
 */
public class CommonUtils {
    public static Boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private static final String URL = "http://api.nytimes.com/svc/search/v2/articlesearch.json?q=%1$s&fq=news_desk:(%2$s)&page=%3$s&begin_date=%4$s&sort=%5$s&api-key=f5571f6befeabe596b4c7979f49b9684:18:74749744";

    public static String getSearchLink(String query, int page) {
        try {
            query = URLEncoder.encode(query, "UTF-8");
            return String.format(URL, query, "\"Business\"", page, "20150310", "newest");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
