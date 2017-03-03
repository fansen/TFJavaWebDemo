package com.tradingfront.API;

import com.ib.client.Contract;
import com.ib.client.TagValue;
import com.tradingfront.Utils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by dell on 2017/1/16.
 *
 */
@Path("/onReqMktDepth")
public class onReqMktDepth {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextReqMktDepth() {
        return "onReqMktDepth";
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLReqMktDepth() {
        return "<?xml version=\"1.0\"?>" + "<hello>onReqMktDepth" + "</hello>";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlReqMktDepth() {
        Contract contract = new Contract();
        contract.symbol("XAUUSD");
        contract.secType("CMDTY");
        contract.exchange("SMART");
        contract.currency("USD");

        int orderId = Utils.getCurrentOrderId();

        ArrayList<TagValue> m_mktDepthOptions = new ArrayList<>();

        Utils.onReqMktDepth(orderId, contract);
        return "<html> " + "<title>" + "onReqMktDepth" + "</title>"
                + "<body><h1>" + "Hello onReqMktDepth" + "</body></h1>" + "</html> ";
    }

}
