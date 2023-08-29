package com.example.distributed_system_a1_server.model;

import com.google.gson.annotations.SerializedName;

public class SkierVerticalResorts {

    private String seasonID = null;

    private Integer totalVert = null;

    public SkierVerticalResorts() {
    }

    public SkierVerticalResorts(String seasonID, Integer totalVert) {
        this.seasonID = seasonID;
        this.totalVert = totalVert;
    }

    public String getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(String seasonID) {
        this.seasonID = seasonID;
    }

    public Integer getTotalVert() {
        return totalVert;
    }

    public void setTotalVert(Integer totalVert) {
        this.totalVert = totalVert;
    }

    @Override
    public String toString() {
        return "{\"SkierVerticalResorts\":{"
                + "\"seasonID\":\"" + seasonID + "\""
                + ", \"totalVert\":\"" + totalVert + "\""
                + "}}";
    }
}
