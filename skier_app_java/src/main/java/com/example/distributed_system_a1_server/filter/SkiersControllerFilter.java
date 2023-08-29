package com.example.distributed_system_a1_server.filter;

import com.example.distributed_system_a1_server.exception.HttpRequestQueryParameterException;
import com.example.distributed_system_a1_server.service.BasicService;
import com.example.distributed_system_a1_server.service.SkiersService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(filterName = "SkierControllerFilter")
public class SkiersControllerFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException {
        MultiReadHttpServletRequest mReq = new MultiReadHttpServletRequest(req);
        final String[] pathParameter = BasicService.getURIPathParameter(mReq);
        if (SkiersService.requestIsWriteNewLiftRide(mReq)) {
            //     API 1
            if (mReq.getContentType() != null && mReq.getContentType().contains(MediaType.APPLICATION_JSON)) {
                try {
                    SkiersService.getLiftRideDetailsWithLiftRide(mReq);
                    chain.doFilter(mReq, res);
                } catch (Exception e) {
                    String msg = e.getMessage();
                    if (e instanceof IllegalStateException) {
                        msg = "Request body no a json.";
                    }
                    if (e instanceof NumberFormatException) {
                        msg = "ResortID or SkierID should be an integer.";
                    }
                    SkiersService.jsonMessageResponse(HttpServletResponse.SC_BAD_REQUEST, msg, res);
                }
            } else {
                SkiersService.jsonMessageResponse(HttpServletResponse.SC_BAD_REQUEST, "Require request content type: " + MediaType.APPLICATION_JSON, res);
            }
        } else if (SkiersService.requestIsGetSkierDayVertical(mReq)) {
            //     API 2
            try {
                SkiersService.getLiftRideDetails(mReq);
                chain.doFilter(mReq, res);
            } catch (Exception e) {
                String msg = e.getMessage();
                if (e instanceof NumberFormatException) {
                    msg = "ResortID or SkierID should be an integer.";
                }
                SkiersService.jsonMessageResponse(HttpServletResponse.SC_BAD_REQUEST, msg, res);
            }
            return;
        } else if (SkiersService.requestIsGetSkierResortTotals(mReq)) {
            //     API 3
            try {
                final String resort = SkiersService.getResort(mReq);
                if (resort == null) {
                    throw new HttpRequestQueryParameterException("Missing required parameter: resort.");
                }
                final Integer skierID = SkiersService.getSkierID(mReq);
                chain.doFilter(mReq, res);
            } catch (Exception e) {
                String msg = e.getMessage();
                if (e instanceof NumberFormatException) {
                    msg = "SkierID should be an integer.";
                }
                SkiersService.jsonMessageResponse(HttpServletResponse.SC_BAD_REQUEST, msg, res);
            }
        } else if (req.getMethod().equals(HttpMethod.OPTIONS)) {
            SkiersService.jsonMessageResponse(HttpServletResponse.SC_ACCEPTED, "", res);
        } else {
            SkiersService.jsonMessageResponse(HttpServletResponse.SC_NOT_FOUND, "No Such Url with Such Parameters: " + mReq.getRequestURL() + Arrays.toString(pathParameter), res);
        }
    }
}
