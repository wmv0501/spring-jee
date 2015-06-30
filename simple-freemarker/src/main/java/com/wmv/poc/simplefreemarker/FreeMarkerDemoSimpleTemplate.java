package com.wmv.poc.simplefreemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wvergara, created on 6/29/15.
 *
 * This poc, retrieves the template from existing file(template.ftl) and insert the values to the specified parameter. And output xsd. (see target folder)
 */

public class FreeMarkerDemoSimpleTemplate {


    public static void main(String[] args) {

        final FreeMarkerConfiguration fmConfig = FreeMarkerConfiguration.getInstance();
        final Configuration cfg = fmConfig.getConfiguration();

        try {
            //Get template from resouces
            Template template = cfg.getTemplate("template.ftl");

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("message", "Hello World!");

            //  product field mocking. ex. from database
            List<String> productField = new ArrayList<String>();
            productField.add("product-id");
            productField.add("reference-order-id");
            productField.add("request-reason");
            productField.add("owens-username");

            data.put("languages", productField);

            Writer out = new StringWriter();

            template.process(data, out);

            writeToFile(out.toString(), "ProductSimpleTemplate.xsd");
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(String content, String filename) {
        URL url = Thread.currentThread().getContextClassLoader().getResource("");

        File file = new File(url.getFile() + filename);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            byte[] bytesArray = content.getBytes();

            fos.write(bytesArray);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

