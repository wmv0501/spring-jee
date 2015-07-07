package com.owens.gis.xsdgenerator.utils;

import com.owens.gis.xsdgenerator.model.Product;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.SchemaOutputResolver;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author wvergara, created on 6/30/15.
 */
public class XsdGenerator {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = null;
        try {


            JAXBContext jaxbContext = JAXBContext.newInstance(Product.class);
            SchemaOutputResolver sor = new CustomSchemaOutputResolver(Product.class.getName());
            jaxbContext.generateSchema(sor);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (fis != null)
                fis.close();

        }


    }
}
