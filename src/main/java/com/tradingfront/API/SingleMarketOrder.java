package com.tradingfront.API;

import com.tradingfront.Utils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by dell on 2017/2/15.
 *
 */
@Path("/SingleMarketOrder")
public class SingleMarketOrder {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextSingleMarketOrder() {
        return "SingleMarketOrder";
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLSingleMarketOrder() {
        return "<?xml version=\"1.0\"?>" + "<hello>SingleMarketOrder" + "</hello>";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlSingleMarketOrder() {
        String account = "DU525701";
        String action = "BUY";
        double quantity = 300;
        Utils.singleMarketOrder(account, action, quantity);
        return "<html> " + "<title>" + "SingleMarketOrder" + "</title>"
                + "<body><h1>" + "Hello SingleMarketOrder" + "</body></h1>" + "</html> ";
    }
}
