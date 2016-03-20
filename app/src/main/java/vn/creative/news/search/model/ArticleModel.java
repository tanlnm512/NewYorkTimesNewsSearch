package vn.creative.news.search.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TanLe on 3/19/16.
 */
public class ArticleModel {
    @SerializedName("web_url")
    private String webUrl;

    @SerializedName("snippet")
    private String snippet;

    @SerializedName("multimedia")
    private List<CoverModel> multimedia;

    public String getWebUrl() {
        return webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public List<CoverModel> getMultimedia() {
        return multimedia;
    }

    @Override
    public String toString() {
        return "ArticleModel{" +
                "webUrl='" + webUrl + '\'' +
                ", snippet='" + snippet + '\'' +
                ", multimedia=" + multimedia +
                '}';
    }
}
