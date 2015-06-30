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
 *   This poc, retrieves the template from String object and write it to file(simple-template.ftl) and insert the values to the specified parameter. And output xsd. (see target folder)
 *   The String content, mocks the process of building the base template, ex. from XML, or POJO > XML, etc.
 */

public class FreeMarkerDemoDynamicTemplate {

    public static void main(String[] args) {
        final String TEMPLATE = "simple-template.ftl";
        final FreeMarkerConfiguration fmConfig = FreeMarkerConfiguration.getInstance();
        final Configuration cfg = fmConfig.getConfiguration();

        try {
            // Load the template
            String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<schema xmlns=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.owens.com/verifications\" xmlns:tns=\"http://www.owens.com/verifications\" elementFormDefault=\"qualified\">\n" +
                    "\n" +
                    "    <element name=\"owens-order\" type=\"tns:cases-type\"></element>\n" +
                    "\n" +
                    "    <complexType name=\"person-type\">\n" +
                    "    \t<sequence minOccurs=\"1\" maxOccurs=\"1\">\n" +
                    "    \t\t<element name=\"reference-subject-id\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"firstname\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"middlename\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"lastname\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"suffix\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"alternate-name\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"birthdate\" type=\"date\"></element>\n" +
                    "    \t\t<element name=\"gender\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"social-security-number\" type=\"string\"></element>\n" +
                    "    \t</sequence>\n" +
                    "    </complexType>\n" +
                    "\n" +
                    "    <complexType name=\"cases-type\">\n" +
                    "    \t<sequence minOccurs=\"1\" maxOccurs=\"unbounded\">\n" +
                    "    \t\t<element name=\"case\" type=\"tns:case-type\"></element>\n" +
                    "    \t</sequence>\n" +
                    "    \t<attribute ref=\"tns:version\"></attribute>\n" +
                    "    </complexType>\n" +
                    "\n" +
                    "    <complexType name=\"case-type\">\n" +
                    "    \t<sequence minOccurs=\"1\" maxOccurs=\"1\">\n" +
                    "     \t\t<element name=\"subject\" type=\"tns:person-type\"></element>\n" +
                    "    \t\t<element name=\"elements\" type=\"tns:elements-type\"></element>\n" +
                    "    \t</sequence>\n" +
                    "    </complexType>\n" +
                    "\n" +
                    "    <attribute name=\"version\">\n" +
                    "    \t<simpleType>\n" +
                    "    \t\t<restriction base=\"string\">\n" +
                    "    \t\t\t<maxLength value=\"4\"></maxLength>\n" +
                    "    \t\t\t<minLength value=\"1\"></minLength>\n" +
                    "    \t\t\t<whiteSpace value=\"collapse\"></whiteSpace>\n" +
                    "    \t\t</restriction>\n" +
                    "    \t</simpleType>\n" +
                    "    </attribute>\n" +
                    "\n" +
                    "    <complexType name=\"elements-type\">\n" +
                    "    \t<sequence minOccurs=\"1\" maxOccurs=\"unbounded\">\n" +
                    "    \t\t<element name=\"element\" type=\"tns:element-type\"></element>\n" +
                    "    \t</sequence>\n" +
                    "    </complexType>\n" +
                    "\n" +
                    "    <complexType name=\"element-type\">\n" +
                    "    \t<sequence minOccurs=\"1\" maxOccurs=\"1\">\n" +
                    "\n" +
                    "    \t\t<element name=\"product-id\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"reference-order-id\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"request-reason\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"owens-username\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"search-details\" type=\"tns:search-details-type\"></element>\n" +
                    "    \t\t<element name=\"result-details\" type=\"tns:result-details-type\"></element>\n" +
                    "    \t\t<element name=\"payment-details\" type=\"tns:payment-details-type\"></element>\n" +
                    "    \t\t<element name=\"attachments\" type=\"tns:attachments-type\"></element>\n" +
                    "    \t</sequence>\n" +
                    "    </complexType>\n" +
                    "\n" +
                    "    <complexType name=\"search-details-type\">\n" +
                    "    \t<sequence>\n" +
                    "    \t\t<element name=\"organization-name\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"campus\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"address-line1\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"address-line2\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"city\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"state-province\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"zip-code\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"country\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"phone\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"fax\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"email\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"website\" type=\"string\"></element>\n" +
                    "    \t</sequence>\n" +
                    "    </complexType>\n" +
                    "\n" +
                    "    <complexType name=\"result-details-type\">\n" +
                    "    \t<sequence>\n" +

                    "<#list productFields as productField>\n" +
                    "            <element name=\"${productField}\" type=\"string\"></element>\n" +
                    "        </#list>" +
                    "    \t</sequence>\n" +
                    "    </complexType>\n" +
                    "\n" +
                    "    <complexType name=\"payment-details-type\">\n" +
                    "    \t<sequence>\n" +
                    "    \t\t<element name=\"owens-payment-id\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"owens-payment-type\" type=\"string\"></element>\n" +
                    "    \t</sequence>\n" +
                    "    </complexType>\n" +
                    "\n" +
                    "    <complexType name=\"attachments-type\">\n" +
                    "    \t<sequence>\n" +
                    "    \t\t<element name=\"attachment\" type=\"string\"></element>\n" +
                    "    \t</sequence>\n" +
                    "    </complexType>\n" +
                    "\n" +
                    "    <complexType name=\"attachment-type\">\n" +
                    "    \t<sequence>\n" +
                    "    \t\t<element name=\"description\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"encoded-content\" type=\"string\"></element>\n" +
                    "    \t\t<element name=\"checksum\" type=\"string\"></element>\n" +
                    "    \t</sequence>\n" +
                    "    </complexType>\n" +
                    "</schema>\n" +
                    "\n" +
                    "\n";

            URL url = Thread.currentThread().getContextClassLoader().getResource("");

            File file = new File(url.getFile() + TEMPLATE);

            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);

            byte[] bytesArray = content.getBytes();

            fos.write(bytesArray);
            fos.flush();
            Template template = cfg.getTemplate(TEMPLATE);

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("message", "Hello World!");

            //  product field mocking. ex. from database
            List<String> productField = new ArrayList<String>();
            productField.add("product-id");
            productField.add("reference-order-id");
            productField.add("request-reason");
            productField.add("owens-username");

            data.put("productFields", productField);

            Writer out = new StringWriter();

            template.process(data, out);

            writeToFile(out.toString(), "ProductDynamicTemplate.xsd");
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

