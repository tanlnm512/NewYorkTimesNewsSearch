package vn.creative.news.search.model;

/**
 * Created by tanlnm on 3/21/2016.
 */
public class SearchSettingModel {
    private long beginTime;
    private String sortOrder;
    private boolean tech;
    private boolean naturalWorld;
    private boolean fashion;

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isTech() {
        return tech;
    }

    public void setTech(boolean tech) {
        this.tech = tech;
    }

    public boolean isNaturalWorld() {
        return naturalWorld;
    }

    public void setNaturalWorld(boolean naturalWorld) {
        this.naturalWorld = naturalWorld;
    }

    public boolean isFashion() {
        return fashion;
    }

    public void setFashion(boolean fashion) {
        this.fashion = fashion;
    }
}
