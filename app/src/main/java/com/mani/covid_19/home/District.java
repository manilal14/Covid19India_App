package com.mani.covid_19.home;

public class District {

    private String name;
    private String conf;
    private String d_conf;

    public District(String name, String conf, String d_conf) {
        this.name = name;
        this.conf = conf;
        this.d_conf = d_conf;
    }

    public String getName() {
        return name;
    }

    public String getConf() {
        return conf;
    }

    public String getD_conf() {
        return d_conf;
    }
}
