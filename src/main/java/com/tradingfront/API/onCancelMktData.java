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
@Path("/onCancelMktData")
public class onCancelMktData {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextCancelMktData() {
        return "onCancelMktData";
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLCancelMktData() {
        return "<?xml version=\"1.0\"?>" + "<hello>onCancelMktData" + "</hello>";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlCancelMktData() {

        int orderId = Utils.getCurrentOrderId();
        Utils.onCancelMktData(orderId);
        return "<html> " + "<title>" + "onCancelMktData" + "</title>"
                + "<body><h1>" + "Hello onCancelMktData" + "</body></h1>" + "</html> ";
    }

}
