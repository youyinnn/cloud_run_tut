package com.example.distributed_system_a1_server.service;

import com.example.distributed_system_a1_server.model.LiftRide;
import com.example.distributed_system_a1_server.model.LiftRideDetails;
import com.example.distributed_system_a1_server.exception.HttpRequestQueryParameterException;
import jakarta.servlet.http.HttpServletRequest;

import javax.ws.rs.HttpMethod;
import java.io.IOException;

/**
 * The type Skiers service.
 */
public class SkiersService extends BasicService {

    /**
     * Request is api 1 boolean.
     *
     * @param req the req
     * @return the boolean
     */
    public static boolean requestIsWriteNewLiftRide(HttpServletRequest req) {
        final String[] pathParameter = BasicService.getURIPathParameter(req);
        return req.getMethod().equals(HttpMethod.POST) && pathParameter.length == 9
                && pathParameter[3].equals("seasons") && pathParameter[5].equals("days")
                && pathParameter[7].equals("skiers");
    }

    /**
     * Request is api 2 boolean.
     *
     * @param req the req
     * @return the boolean
     */
    public static boolean requestIsGetSkierDayVertical(HttpServletRequest req) {
        final String[] pathParameter = BasicService.getURIPathParameter(req);
        return req.getMethod().equals(HttpMethod.GET) && pathParameter.length == 9
                && pathParameter[3].equals("seasons") && pathParameter[5].equals("days")
                && pathParameter[7].equals("skiers");
    }

    /**
     * Request is api 3 boolean.
     *
     * @param req the req
     * @return the boolean
     */
    public static boolean requestIsGetSkierResortTotals(HttpServletRequest req) {
        final String[] pathParameter = BasicService.getURIPathParameter(req);
        return req.getMethod().equals(HttpMethod.GET) && pathParameter.length == 4 && pathParameter[3].equals("vertical");
    }

    /**
     * Gets lift ride details for API 2.
     *
     * @param req the req
     * @return the lift ride details
     */
    public static LiftRideDetails getLiftRideDetails(HttpServletRequest req) {
        final String[] pathParameter = SkiersService.getURIPathParameter(req);

        final String seasonID = (pathParameter[4]);
        final String dayID = (pathParameter[6]);
        final Integer resortID = Integer.valueOf(pathParameter[2]);
        final Integer skierID = Integer.valueOf(pathParameter[8]);

        return new LiftRideDetails(resortID, seasonID, dayID, skierID, null);
    }

    /**
     * Gets lift ride details with lift ride for API 1.
     *
     * @param req the req
     * @return the lift ride details with lift ride
     * @throws IOException the io exception
     */
    public static LiftRideDetails getLiftRideDetailsWithLiftRide(HttpServletRequest req) throws IOException {
        LiftRideDetails liftRideDetails = getLiftRideDetails(req);
        final LiftRide liftRide = SkiersService.getRequestBodyAsTypeObject(req, LiftRide.class);
        if (liftRide.getLiftID() == null || liftRide.getTime() == null) {
            throw new HttpRequestQueryParameterException("Missing required parameter: liftID or time.");
        }

        liftRideDetails.setLiftRide(liftRide);
        return liftRideDetails;
    }

    public static Integer getSkierID(HttpServletRequest req) {
        final String[] pathParameter = SkiersService.getURIPathParameter(req);
        return Integer.valueOf(pathParameter[2]);
    }

    public static String getResort(HttpServletRequest req) {
        return req.getParameter("resort");
    }

    public static String[] getSeason(HttpServletRequest req) {
        return req.getParameterValues("season");
    }

}
