
package com.google.movessample.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyModel {

    @SerializedName("ip")
    @Expose
    private String ip;
    @SerializedName("is_eu")
    @Expose
    private Boolean isEu;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("region_code")
    @Expose
    private String regionCode;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("continent_name")
    @Expose
    private String continentName;
    @SerializedName("continent_code")
    @Expose
    private String continentCode;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("asn")
    @Expose
    private String asn;
    @SerializedName("organisation")
    @Expose
    private String organisation;
    @SerializedName("postal")
    @Expose
    private String postal;
    @SerializedName("calling_code")
    @Expose
    private String callingCode;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("emoji_flag")
    @Expose
    private String emojiFlag;
    @SerializedName("emoji_unicode")
    @Expose
    private String emojiUnicode;
    @SerializedName("languages")
    @Expose
    private List<Language> languages = null;
    @SerializedName("currency")
    @Expose
    private Currency currency;
    @SerializedName("time_zone")
    @Expose
    private TimeZone timeZone;
    @SerializedName("threat")
    @Expose
    private Threat threat;
    @SerializedName("count")
    @Expose
    private String count;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Boolean getIsEu() {
        return isEu;
    }

    public void setIsEu(Boolean isEu) {
        this.isEu = isEu;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getContinentCode() {
        return continentCode;
    }

    public void setContinentCode(String continentCode) {
        this.continentCode = continentCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAsn() {
        return asn;
    }

    public void setAsn(String asn) {
        this.asn = asn;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCallingCode() {
        return callingCode;
    }

    public void setCallingCode(String callingCode) {
        this.callingCode = callingCode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getEmojiFlag() {
        return emojiFlag;
    }

    public void setEmojiFlag(String emojiFlag) {
        this.emojiFlag = emojiFlag;
    }

    public String getEmojiUnicode() {
        return emojiUnicode;
    }

    public void setEmojiUnicode(String emojiUnicode) {
        this.emojiUnicode = emojiUnicode;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public Threat getThreat() {
        return threat;
    }

    public void setThreat(Threat threat) {
        this.threat = threat;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

}
