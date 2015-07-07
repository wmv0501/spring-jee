package com.owens.gis.xsdgenerator.utils;

import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;

/**
 * @author wvergara, created on 6/30/15.
 */
public class CustomSchemaOutputResolver extends SchemaOutputResolver {
    private StringWriter stringWriter = new StringWriter();

    private String filename;

    public CustomSchemaOutputResolver(String filename) {
        this.filename = filename;
    }

    public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("");

        // create new file
        File file = new File(url.getFile()+filename+".xsd");

        // create stream result
        StreamResult result = new StreamResult(stringWriter);
        StreamResult result2 = new StreamResult(stringWriter);

        // set system id
        result.setSystemId(file.toURI().toURL().toString());
        result2.setSystemId(file.toURI().toURL().toString());

        // return result
        return result;


    }

    public String getSchema() {
        return stringWriter.toString();
    }

}

