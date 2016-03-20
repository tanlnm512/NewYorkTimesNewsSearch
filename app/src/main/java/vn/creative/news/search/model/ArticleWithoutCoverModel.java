package vn.creative.news.search.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TanLe on 3/19/16.
 */
public class ArticleWithoutCoverModel {
    private String webUrl;

    private String snippet;

    public ArticleWithoutCoverModel(String webUrl, String snippet) {
        this.webUrl = webUrl;
        this.snippet = snippet;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    @Override
    public String toString() {
        return "ArticleWithoutCoverModel{" +
                "webUrl='" + webUrl + '\'' +
                ", snippet='" + snippet + '\'' +
                '}';
    }
}
