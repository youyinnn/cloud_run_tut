package com.example.distributed_system_a1_server.model;

public class LiftRide {

    private Integer time;
    private Integer liftID;

    public LiftRide() {
    }

    public LiftRide(Integer time, Integer liftID) {
        this.time = time;
        this.liftID = liftID;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getLiftID() {
        return liftID;
    }

    public void setLiftID(Integer liftID) {
        this.liftID = liftID;
    }

    @Override
    public String toString() {
        return "{\"LiftRide\":{" + "\"time\":\"" + time + "\"" + ", \"liftID\":\"" + liftID + "\"" + "}}";
    }
}
