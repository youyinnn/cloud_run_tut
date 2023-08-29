package com.example.distributed_system_a1_server.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "demo", urlPatterns = "/demo/*")
public class DemoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // RequestDispatcher rd = req.getRequestDispatcher("../public/index.html");
        // rd.forward(req, resp);
        resp.sendRedirect("../public/index.html");
    }
}
