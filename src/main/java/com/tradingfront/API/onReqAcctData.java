package com.tradingfront.API;

import com.tradingfront.Utils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by dell on 2017/1/13.
 *
 */
@Path("/onReqAcctData")
public class onReqAcctData {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextReqAcctData() {
        return "onReqAcctData";
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLReqAcctData() {
        return "<?xml version=\"1.0\"?>" + "<hello>onReqAcctData" + "</hello>";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlReqAcctData() {
        boolean subscribe = true;
        String acctCode = "DU525700";
        Utils.onReqAcctData(subscribe, acctCode);
        return "<html> " + "<title>" + "onReqAcctData" + "</title>"
                + "<body><h1>" + "Hello onReqAcctData" + "</body></h1>" + "</html> ";
    }
}
