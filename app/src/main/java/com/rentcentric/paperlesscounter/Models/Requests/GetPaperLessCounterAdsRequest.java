package com.rentcentric.paperlesscounter.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPaperLessCounterAdsRequest {

    @SerializedName("ClientID")
    @Expose
    private String clientID;
    @SerializedName("Server")
    @Expose
    private String server;

    public GetPaperLessCounterAdsRequest(String clientID, String server) {
        this.clientID = clientID;
        this.server = server;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}