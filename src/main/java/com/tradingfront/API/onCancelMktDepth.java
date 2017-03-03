package com.tradingfront.API;

import com.tradingfront.Utils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by dell on 2017/1/16.
 *
 */
@Path("/onCancelMktDepth")
public class onCancelMktDepth {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextCancelMktDepth() {
        return "onCancelMktDepth";
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLCancelMktDepth() {
        return "<?xml version=\"1.0\"?>" + "<hello>onCancelMktDepth" + "</hello>";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlCancelMktDepth() {

        int orderId = Utils.getCurrentOrderId();
        Utils.onCancelMktDepth(orderId);
        return "<html> " + "<title>" + "onCancelMktDepth" + "</title>"
                + "<body><h1>" + "Hello onCancelMktDepth" + "</body></h1>" + "</html> ";
    }
}
