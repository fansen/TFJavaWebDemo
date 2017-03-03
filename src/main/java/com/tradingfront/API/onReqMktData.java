package com.tradingfront.API;

import com.ib.client.Contract;
import com.tradingfront.Utils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by dell on 2017/1/16.
 *
 */
@Path("/onReqMktData")
public class onReqMktData {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextReqMktData() {
        return "onReqMktData";
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLReqMktData() {
        return "<?xml version=\"1.0\"?>" + "<hello>onReqMktData" + "</hello>";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlReqMktData() {
        Contract contract = new Contract();
        contract.symbol("XAUUSD");
        contract.secType("CMDTY");
        contract.exchange("SMART");
        contract.currency("USD");

        int orderId = Utils.getCurrentOrderId();

        Utils.onReqMktData(orderId, contract);
        return "<html> " + "<title>" + "onReqMktData" + "</title>"
                + "<body><h1>" + "Hello onReqMktData" + "</body></h1>" + "</html> ";
    }
}
