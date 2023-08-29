package com.example.distributed_system_a1_server.model;

import java.util.ArrayList;
import java.util.List;

public class SkierVertical {

    private List<SkierVerticalResorts> resorts;

    public SkierVertical() {
    }

    public SkierVertical(List<SkierVerticalResorts> resorts) {
        this.resorts = resorts;
    }

    public List<SkierVerticalResorts> getResorts() {
        return resorts;
    }

    public void setResorts(List<SkierVerticalResorts> resorts) {
        this.resorts = resorts;
    }

    @Override
    public String toString() {
        return "{\"SkierVertical\":{"
                + "\"resorts\":" + resorts
                + "}}";
    }
}
