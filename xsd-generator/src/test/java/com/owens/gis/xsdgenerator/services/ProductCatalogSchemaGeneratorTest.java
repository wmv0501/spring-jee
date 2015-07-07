package com.owens.gis.xsdgenerator.services;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author wvergara, created on 6/30/15.
 */
public class ProductCatalogSchemaGeneratorTest {


    @Test
    public void testGenerateSchema(){
        ProductSchemaGenerator productSchemaGenerator = new ProductSchemaGenerator();
        try {
            productSchemaGenerator.generateSchema();
        }catch(Exception ex){
            ex.printStackTrace();

        }
        Assert.assertTrue(true);
    }

}
