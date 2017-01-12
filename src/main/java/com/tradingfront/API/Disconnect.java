package com.tradingfront.API;

import com.tradingfront.Utils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by dell on 2017/1/12.
 *
 */
@Path("/Disconnect")
public class Disconnect {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextDisConnect() {
        return "Disconnect";
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLDisConnect() {
        return "<?xml version=\"1.0\"?>" + "<hello>Disconnect" + "</hello>";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlDisConnect() {
        Utils.Disconnect();
        return "<html> " + "<title>" + "Disconnect" + "</title>"
                + "<body><h1>" + "Hello Disconnect" + "</body></h1>" + "</html> ";
    }
}
