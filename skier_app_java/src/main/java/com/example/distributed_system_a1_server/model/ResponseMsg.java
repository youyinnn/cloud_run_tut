package com.example.distributed_system_a1_server.model;

public class ResponseMsg {

    private String message;


    public ResponseMsg() {
    }


    public ResponseMsg(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{\"ResponseMsg\":{"
                + "\"message\":\"" + message + "\""
                + "}}";
    }
}
