package com.example.distributed_system_a1_server.model;

public class LiftRideDetails {

    private Integer resortID;
    private String seasonID;
    private String dayID;
    private Integer skierID;

    private LiftRide liftRide;

    public LiftRideDetails() {
    }

    public LiftRideDetails(Integer resortID, String seasonID, String dayID, Integer skierID, LiftRide liftRide) {
        this.resortID = resortID;
        this.seasonID = seasonID;
        this.dayID = dayID;
        this.skierID = skierID;
        this.liftRide = liftRide;
    }

    public Integer getResortID() {
        return resortID;
    }

    public void setResortID(Integer resortID) {
        this.resortID = resortID;
    }

    public String getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(String seasonID) {
        this.seasonID = seasonID;
    }

    public String getDayID() {
        return dayID;
    }

    public void setDayID(String dayID) {
        this.dayID = dayID;
    }

    public Integer getSkierID() {
        return skierID;
    }

    public void setSkierID(Integer skierID) {
        this.skierID = skierID;
    }

    public LiftRide getLiftRide() {
        return liftRide;
    }

    public void setLiftRide(LiftRide liftRide) {
        this.liftRide = liftRide;
    }

    @Override
    public String toString() {
        return "{\"LiftRideDetails\":{"
                + "\"resortID\":\"" + resortID + "\""
                + ", \"seasonID\":\"" + seasonID + "\""
                + ", \"dayID\":\"" + dayID + "\""
                + ", \"skierID\":\"" + skierID + "\""
                + ", \"liftRide\":" + liftRide
                + "}}";
    }
}
