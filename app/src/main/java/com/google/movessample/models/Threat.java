
package com.google.movessample.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Threat {

    @SerializedName("is_tor")
    @Expose
    private Boolean isTor;
    @SerializedName("is_proxy")
    @Expose
    private Boolean isProxy;
    @SerializedName("is_anonymous")
    @Expose
    private Boolean isAnonymous;
    @SerializedName("is_known_attacker")
    @Expose
    private Boolean isKnownAttacker;
    @SerializedName("is_known_abuser")
    @Expose
    private Boolean isKnownAbuser;
    @SerializedName("is_threat")
    @Expose
    private Boolean isThreat;
    @SerializedName("is_bogon")
    @Expose
    private Boolean isBogon;

    public Boolean getIsTor() {
        return isTor;
    }

    public void setIsTor(Boolean isTor) {
        this.isTor = isTor;
    }

    public Boolean getIsProxy() {
        return isProxy;
    }

    public void setIsProxy(Boolean isProxy) {
        this.isProxy = isProxy;
    }

    public Boolean getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public Boolean getIsKnownAttacker() {
        return isKnownAttacker;
    }

    public void setIsKnownAttacker(Boolean isKnownAttacker) {
        this.isKnownAttacker = isKnownAttacker;
    }

    public Boolean getIsKnownAbuser() {
        return isKnownAbuser;
    }

    public void setIsKnownAbuser(Boolean isKnownAbuser) {
        this.isKnownAbuser = isKnownAbuser;
    }

    public Boolean getIsThreat() {
        return isThreat;
    }

    public void setIsThreat(Boolean isThreat) {
        this.isThreat = isThreat;
    }

    public Boolean getIsBogon() {
        return isBogon;
    }

    public void setIsBogon(Boolean isBogon) {
        this.isBogon = isBogon;
    }

}
