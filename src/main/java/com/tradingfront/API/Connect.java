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
@Path("/Connect")
public class Connect {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextConnect() {
        return "Connect";
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLConnect() {
        return "<?xml version=\"1.0\"?>" + "<hello>Connect" + "</hello>";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlConnect() {
        String m_reqIp = "127.0.0.1";
        int m_reqPort = 7497;
        int m_reqClientId = 0;
        Utils.Connect(m_reqIp, m_reqPort, m_reqClientId);
        return "<html> " + "<title>" + "Connect" + "</title>"
                + "<body><h1>" + "Hello Connect" + "</body></h1>" + "</html> ";
    }
}
