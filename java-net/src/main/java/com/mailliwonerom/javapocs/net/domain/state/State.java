package com.mailliwonerom.javapocs.net.domain.state;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class State {
    @SerializedName("state")
    private String state;
    @SerializedName("notes")
    private String notes;
    @SerializedName("covid19Site")
    private String covidSite;
    @SerializedName("covid19SiteSecondary")
    private String covidSiteSecondary;
    @SerializedName("covid19SiteTertiary")
    private String covidSiteTertiary;
    @SerializedName("covid19SiteQuaternary")
    private String covidSiteQuaternary;
    @SerializedName("covid19SiteQuinary")
    private String covidSiteQuinary;
    @SerializedName("twitter")
    private String twitter;
    @SerializedName("covid19SiteOld")
    private String covidSiteOld;
    @SerializedName("covidTrackingProjectPreferredTotalTestUnits")
    private String covidTrackingUnits;
    @SerializedName("covidTrackingProjectPreferredTotalTestField")
    private String covidTrackingField;
    @SerializedName("totalTestResultsField")
    private String totalTestResults;
    @SerializedName("pui")
    private String pui;
    @SerializedName("pum")
    private String pum;
    @SerializedName("name")
    private String name;
    @SerializedName("fips")
    private String fips;

    public State() {}

    public State(String state, String notes, String covidSite,
        String covidSiteSecondary, String covidSiteTertiary,
        String covidSiteQuaternary, String covidSiteQuinary,
        String twitter, String covidSiteOld,
        String covidTrackingUnits, String covidTrackingField,
        String totalTestResults, String pui, String pum, String name,
        String fips) {

        this.state = state;
        this.notes = notes;
        this.covidSite = covidSite;
        this.covidSiteSecondary = covidSiteSecondary;
        this.covidSiteTertiary = covidSiteTertiary;
        this.covidSiteQuaternary = covidSiteQuaternary;
        this.covidSiteQuinary = covidSiteQuinary;
        this.twitter = twitter;
        this.covidSiteOld = covidSiteOld;
        this.covidTrackingUnits = covidTrackingUnits;
        this.covidTrackingField = covidTrackingField;
        this.totalTestResults = totalTestResults;
        this.pui = pui;
        this.pum = pum;
        this.name = name;
        this.fips = fips;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCovidSite() {
        return covidSite;
    }

    public void setCovidSite(String covidSite) {
        this.covidSite = covidSite;
    }

    public String getCovidSiteSecondary() {
        return covidSiteSecondary;
    }

    public void setCovidSiteSecondary(String covidSiteSecondary) {
        this.covidSiteSecondary = covidSiteSecondary;
    }

    public String getCovidSiteTertiary() {
        return covidSiteTertiary;
    }

    public void setCovidSiteTertiary(String covidSiteTertiary) {
        this.covidSiteTertiary = covidSiteTertiary;
    }

    public String getCovidSiteQuaternary() {
        return covidSiteQuaternary;
    }

    public void setCovidSiteQuaternary(String covidSiteQuaternary) {
        this.covidSiteQuaternary = covidSiteQuaternary;
    }

    public String getCovidSiteQuinary() {
        return covidSiteQuinary;
    }

    public void setCovidSiteQuinary(String covidSiteQuinary) {
        this.covidSiteQuinary = covidSiteQuinary;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getCovidSiteOld() {
        return covidSiteOld;
    }

    public void setCovidSiteOld(String covidSiteOld) {
        this.covidSiteOld = covidSiteOld;
    }

    public String getCovidTrackingUnits() {
        return covidTrackingUnits;
    }

    public void setCovidTrackingUnits(String covidTrackingUnits) {
        this.covidTrackingUnits = covidTrackingUnits;
    }

    public String getCovidTrackingField() {
        return covidTrackingField;
    }

    public void setCovidTrackingField(String covidTrackingField) {
        this.covidTrackingField = covidTrackingField;
    }

    public String getTotalTestResults() {
        return totalTestResults;
    }

    public void setTotalTestResults(String totalTestResults) {
        this.totalTestResults = totalTestResults;
    }

    public String getPui() {
        return pui;
    }

    public void setPui(String pui) {
        this.pui = pui;
    }

    public String getPum() {
        return pum;
    }

    public void setPum(String pum) {
        this.pum = pum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFips() {
        return fips;
    }

    public void setFips(String fips) {
        this.fips = fips;
    }

    @Override
    public String toString() { return new Gson().toJson(this); }
}
