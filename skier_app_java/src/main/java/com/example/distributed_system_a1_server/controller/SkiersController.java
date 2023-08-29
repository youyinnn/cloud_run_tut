package com.example.distributed_system_a1_server.controller;

import com.example.distributed_system_a1_server.constant.UrlContext;
import com.example.distributed_system_a1_server.filter.MultiReadHttpServletRequest;
import com.example.distributed_system_a1_server.model.LiftRideDetails;
import com.example.distributed_system_a1_server.model.SkierVertical;
import com.example.distributed_system_a1_server.model.SkierVerticalResorts;
import com.example.distributed_system_a1_server.service.SkiersService;
import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "skier", urlPatterns = UrlContext.SKIERS_CONTEXT + "/*")
public class SkiersController extends HttpServlet {

    private final static String QUEUE_NAME = "ds2022";
    private static Connection connection;
    private static Channel channel;

    @Override
    public void init() throws ServletException {
        super.init();
        // ConnectionFactory factory = new ConnectionFactory();
        // factory.setHost("168.138.68.23");
        // try {
        //     connection = factory.newConnection();
        //     channel = connection.createChannel();
        //     channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //
        // } catch (IOException | TimeoutException e) {
        //     throw new RuntimeException(e);
        // }
    }

    @Override


    public void destroy() {
        super.destroy();
        // try {
        //     channel.close();
        //     connection.close();
        // } catch (IOException | TimeoutException e) {
        //     throw new RuntimeException(e);
        // }
    }

    public void sendToQueue(String msg) throws IOException {
        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties(
                MediaType.APPLICATION_JSON,
                StandardCharsets.UTF_8.toString(),
                null, null, null, null, null, null,
                UUID.randomUUID().toString(), null, null,null, null,null);
        channel.basicPublish("", QUEUE_NAME, basicProperties, msg.getBytes());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println(1);
        req = new MultiReadHttpServletRequest(req);
        if (SkiersService.requestIsWriteNewLiftRide(req)) {
            final LiftRideDetails liftRideDetailsWithLiftRide
                    = SkiersService.getLiftRideDetailsWithLiftRide(req);
            System.out.println(liftRideDetailsWithLiftRide);
            SkiersService.statusCodeResponseOnly(HttpServletResponse.SC_CREATED, resp);
            Gson g = new Gson();
            final String formattedMsg = g.toJson(liftRideDetailsWithLiftRide, LiftRideDetails.class);
            // sendToQueue(formattedMsg);
            SkiersService.jsonMessageResponse(HttpServletResponse.SC_CREATED, formattedMsg, resp);
        } else {
            SkiersService.jsonMessageResponse(HttpServletResponse.SC_BAD_REQUEST, "Dummy MSG.", resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req = new MultiReadHttpServletRequest(req);
        if (SkiersService.requestIsGetSkierDayVertical(req)) {
            final LiftRideDetails liftRideDetails
                    = SkiersService.getLiftRideDetails(req);
            System.out.println(liftRideDetails);
            SkiersService.integerSuccessResponse(ThreadLocalRandom.current().nextInt(), resp);
        } else {
            final String resort = SkiersService.getResort(req);
            final Integer skierID = SkiersService.getSkierID(req);
            final String[] season = SkiersService.getSeason(req);
            System.out.println(resort);
            System.out.println(skierID);
            System.out.println(Arrays.toString(season));

            List<SkierVerticalResorts> skierVerticalResorts = new ArrayList<>();
            skierVerticalResorts.add(new SkierVerticalResorts("a", 123));
            skierVerticalResorts.add(new SkierVerticalResorts("b", 456));
            skierVerticalResorts.add(new SkierVerticalResorts("c", 789));
            SkierVertical  skierVertical = new SkierVertical(skierVerticalResorts);
            SkiersService.objectSuccessResponse(skierVertical, resp);
        }
        // SkiersService.jsonResponse(HttpServletResponse.SC_OK, "Dummy MSG.", resp);
    }

}
