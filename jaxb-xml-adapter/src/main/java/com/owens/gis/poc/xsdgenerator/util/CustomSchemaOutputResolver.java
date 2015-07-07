package com.owens.gis.poc.xsdgenerator.util;

import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author wvergara, created on 6/30/15.
 */
public class CustomSchemaOutputResolver extends SchemaOutputResolver {
    public static final String DEFAULT_FILENAME = "ProductSchemaRaw.xsd";
    public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {


        URL url = Thread.currentThread().getContextClassLoader().getResource("");

        // create new file
        File file = new File(url.getFile()+DEFAULT_FILENAME);

        // create stream result
        StreamResult result = new StreamResult(file);

        // set system id
        result.setSystemId(file.toURI().toURL().toString());

        // return result
        return result;


    }

}

