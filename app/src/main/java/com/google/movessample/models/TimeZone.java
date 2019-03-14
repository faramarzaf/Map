
package com.google.movessample.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeZone {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("abbr")
    @Expose
    private String abbr;
    @SerializedName("offset")
    @Expose
    private String offset;
    @SerializedName("is_dst")
    @Expose
    private Boolean isDst;
    @SerializedName("current_time")
    @Expose
    private String currentTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public Boolean getIsDst() {
        return isDst;
    }

    public void setIsDst(Boolean isDst) {
        this.isDst = isDst;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

}
