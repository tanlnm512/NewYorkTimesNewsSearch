package vn.creative.news.search.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TanLe on 3/19/16.
 */
public class CoverModel {
    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("url")
    private String url;

    @SerializedName("subtype")
    private String subtype;

    @SerializedName("type")
    private String type;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getUrl() {
        return url;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "CoverModel{" +
                "width=" + width +
                ", height=" + height +
                ", url='" + url + '\'' +
                ", subtype='" + subtype + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
