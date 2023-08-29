package com.example.distributed_system_a1_server.exception;

public class HttpRequestQueryParameterException extends RuntimeException {

    public HttpRequestQueryParameterException(String message) {
        super(message);
    }

    public HttpRequestQueryParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
