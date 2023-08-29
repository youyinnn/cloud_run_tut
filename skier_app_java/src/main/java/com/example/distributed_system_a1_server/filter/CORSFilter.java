package com.example.distributed_system_a1_server.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "CORSFilter")
public class CORSFilter extends HttpFilter {

    public static void setAccessControlHeaders(HttpServletResponse httpResponse) {
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpResponse.setHeader("Access-Control-Allow-Headers", "*");
        // httpResponse.setHeader("Access-Control-Max-Age", "86400");
        // // httpResponse.setHeader("Access-Control-Allow-Credentials", "false");
        // httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        setAccessControlHeaders(res);
        chain.doFilter(req, res);
    }
}
