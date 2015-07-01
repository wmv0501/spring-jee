package com.wmv.poc;

import com.wmv.poc.jpa.poc.MySchemaOutputResolver;
import com.wmv.poc.jpa.poc.Product;

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
            SchemaOutputResolver sor = new MySchemaOutputResolver();
//        sor.createOutput("/Users/wvergara/","wmv.xsd");
            jaxbContext.generateSchema(sor);

        /*File fi = new File("/Users/wvergara/wmv.xsd");
        fis = new FileInputStream(fi);

        int i=0;
        while((i=fis.read())!=-1)
        {
            char c=(char)i;
            System.out.print(c);
        }*/
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (fis != null)
                fis.close();

        }


    }
}
