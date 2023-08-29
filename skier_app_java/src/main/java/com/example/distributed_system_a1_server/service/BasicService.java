package com.example.distributed_system_a1_server.service;

import com.example.distributed_system_a1_server.model.ResponseMsg;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.stream.Collectors;


public class BasicService {

    public static String[] getURIPathParameter(HttpServletRequest req) {
        return req.getRequestURI().replace(req.getContextPath(), "").split("/");
    }

    public static <T> T getRequestBodyAsTypeObject(HttpServletRequest req, Class<T> clazz) throws IOException {
        final String body = getRequestBodyAsString(req);
        Gson g = new Gson();
        return g.fromJson(body, clazz);
    }

    public static String getRequestBodyAsString(HttpServletRequest req) throws IOException {
        return req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    }

    public static void jsonMessageResponse(int status, String msg, HttpServletResponse resp) throws IOException {
        Gson g = new Gson();
        ResponseMsg rMsg = new ResponseMsg(msg);
        resp.setContentType(MediaType.APPLICATION_JSON);
        resp.setStatus(status);
        resp.getOutputStream().print(g.toJson(rMsg));
        resp.getOutputStream().flush();
    }

    public static <T> void objectSuccessResponse(T obj, HttpServletResponse resp) throws IOException {
        Gson g = new Gson();
        resp.setContentType(MediaType.APPLICATION_JSON);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getOutputStream().print(g.toJson(obj));
        resp.getOutputStream().flush();
    }

    public static void integerSuccessResponse(Integer integer, HttpServletResponse resp) throws IOException {
        resp.setContentType(MediaType.APPLICATION_JSON);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getOutputStream().print(integer);
        resp.getOutputStream().flush();
    }

    public static void statusCodeResponseOnly(Integer status, HttpServletResponse resp) {
        resp.setStatus(status);
    }
}
