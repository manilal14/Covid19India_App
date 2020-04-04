package com.mani.covid_19.home;

public class State {

    private String name;
    private String active;
    private String confired;
    private String deaths;
    private String recovered;

    private String lastUpdateAt;

    private String d_act;
    private String d_conf;
    private String d_deaths;
    private String d_covered;

    public State(String name, String active, String confired, String deaths, String recovered, String lastUpdateAt,
                 String d_act, String d_conf, String d_deaths, String d_covered) {
        this.name = name;
        this.active = active;
        this.confired = confired;
        this.deaths = deaths;
        this.recovered = recovered;
        this.lastUpdateAt = lastUpdateAt;
        this.d_act = d_act;
        this.d_conf = d_conf;
        this.d_deaths = d_deaths;
        this.d_covered = d_covered;
    }

    public String getName() {
        return name;
    }

    public String getActive() {
        return active;
    }

    public String getConfired() {
        return confired;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getLastUpdateAt() {
        return lastUpdateAt;
    }

    public String getD_act() {
        return d_act;
    }

    public String getD_conf() {
        return d_conf;
    }

    public String getD_deaths() {
        return d_deaths;
    }

    public String getD_covered() {
        return d_covered;
    }
}
