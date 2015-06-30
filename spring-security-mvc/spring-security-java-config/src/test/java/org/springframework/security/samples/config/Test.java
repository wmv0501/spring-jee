package org.springframework.security.samples.config;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.net.httpserver.HttpServer;
import org.junit.Before;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import static junit.framework.Assert.assertEquals;

/**
 * @author wvergara, created on 6/11/15.
 */
public class Test {

    static final URI BASE_URI = getBaseURI();
    HttpServer server;

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://ws.dev1.owens.dev").port(15800).build();
    }




    @org.junit.Test
    public void testGetDefaultUser() throws IOException {
        Client client = Client.create(new DefaultClientConfig());
        WebResource service = client.resource(getBaseURI());
        ClientResponse resp = service.path("ttsvr/OrderFormService/saveOrderFormDetails")
                .accept(MediaType.APPLICATION_XML)

                .post(ClientResponse.class, readFile("/Users/wvergara/_Development/_temp/payload.xml"));
        System.out.println("Got stuff: " + resp);
        System.out.println("Payload: " +readFile("/Users/wvergara/_Development/_temp/payload.xml") );
        String text = resp.getEntity(String.class);

        assertEquals(200, resp.getStatus());
        System.out.println( text);
    }

    public String readFile(String path) throws IOException{
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String sCurrentLine = "";
            while (( sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
            }

        }

        return sb.toString();
    }
}
