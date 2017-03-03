package com.tradingfront;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by dell on 2017/1/16.
 *
 * This is a Test
 */
@Path("UserInfoService")
public class UserInfo {

    // @GET here defines, this method will process HTTP GET requests.
    @GET
    // @Path here defines method level path. Identifies the URI path that a
    // resource class method will serve requests for.
    @Path("/name/{i}")
    // @Produces here defines the media type(s) that the methods
    // of a resource class can produce.
    @Produces(MediaType.TEXT_XML)
    // @PathParam injects the value of URI parameter that defined in @Path
    // expression, into the method.
    public String userName(@PathParam("i") String i) {

        String name = i;
        return "<User>" + "<Name>" + name + "</Name>" + "</User>";
    }

    @POST
    @Path("/age/{j}")
    @Produces(MediaType.TEXT_XML)
    public String userAge(@PathParam("j") int j) {

        int age = j;
        System.out.println("Log: age = " + age);
        return "<User>" + "<Age>" + age + "</Age>" + "</User>";
    }

}
