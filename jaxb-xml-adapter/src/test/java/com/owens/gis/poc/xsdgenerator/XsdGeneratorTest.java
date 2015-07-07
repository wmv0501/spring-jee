package com.owens.gis.poc.xsdgenerator;

import com.owens.gis.poc.xsdgenerator.model.Product;
import com.owens.gis.poc.xsdgenerator.util.CustomSchemaOutputResolver;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.SchemaOutputResolver;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author wvergara, created on 6/30/15.
 */
public class XsdGeneratorTest {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Product.class);
            SchemaOutputResolver sor = new CustomSchemaOutputResolver();
            jaxbContext.generateSchema(sor);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (fis != null)
                fis.close();

        }


    }
}
