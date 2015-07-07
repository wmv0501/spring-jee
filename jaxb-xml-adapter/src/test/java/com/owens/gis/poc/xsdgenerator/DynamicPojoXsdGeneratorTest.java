package com.owens.gis.poc.xsdgenerator;

import com.owens.gis.poc.xsdgenerator.model.Product;
import com.owens.gis.poc.xsdgenerator.util.CustomSchemaOutputResolver;
import com.owens.gis.poc.xsdgenerator.util.JaxbPojoBuilder;
import com.owens.gis.poc.xsdgenerator.util.PojoBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.SchemaOutputResolver;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wvergara, created on 7/2/15.
 */
public class DynamicPojoXsdGeneratorTest {
    public static void main(String[] args) throws Exception {

        //Mocking data from database.
        Map<String, Class<?>> classVar = new HashMap<String, Class<?>>();
        classVar.put("integerObject", Integer.class);
        classVar.put("stringObject", String.class);

        Map<String, Class<?>> classVar2 = new HashMap<String, Class<?>>();
        classVar.put("booleanObject", Integer.class);
        classVar.put("doubleObject", String.class);


        PojoBuilder pojoBuilder = new JaxbPojoBuilder("com.owens.gis.poc.xsdgenerator.model.ProductField")
                .withClassVariables(classVar);

        // showing usage of builder
        for (Map.Entry<String, Class<?>> additionlClsVar : classVar.entrySet()) {
            pojoBuilder.withClassVariable(additionlClsVar.getKey(), additionlClsVar.getValue());
        }

        Class<?> clazz = pojoBuilder.build();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Product.class);
            SchemaOutputResolver sor = new CustomSchemaOutputResolver();
            jaxbContext.generateSchema(sor);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
