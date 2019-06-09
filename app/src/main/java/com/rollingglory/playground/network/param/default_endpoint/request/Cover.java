package com.rollingglory.playground.network.param.default_endpoint.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cover {
    @SerializedName("big")
    @Expose
    private String big;
    @SerializedName("medium")
    @Expose
    private String medium;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
