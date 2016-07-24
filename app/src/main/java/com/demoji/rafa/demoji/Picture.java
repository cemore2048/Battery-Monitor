package com.demoji.rafa.demoji;

/**
 * Created by rafa on 7/23/16.
 */
public class Picture {

    private String id;
    private String secret;
    private String server;
    private String farm;

    public void setId(String id) {
        this.id = id;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }

    public String getFarm() {
        return farm;
    }
}
