package com.owens.gis.poc.xsdgenerator;

import com.google.common.collect.ImmutableList;
import com.owens.gis.poc.xsdgenerator.model.ProductFieldU;
import com.owens.gis.poc.xsdgenerator.model.ProductU;
import com.owens.gis.poc.xsdgenerator.util.ElementGenericType;
import com.owens.gis.poc.xsdgenerator.util.ElementGenericTypeAdapter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.annotation.ElementType;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class XmlUnmarshallTest {

    public static void main(String[] args) throws Exception {

        JAXBContext jc = JAXBContext.newInstance(ProductU.class, ElementGenericType.class);
        ElementGenericTypeAdapter adapter = new ElementGenericTypeAdapter(jc);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        unmarshaller.setAdapter(adapter);
        URL url = Thread.currentThread().getContextClassLoader().getResource("");

        File xml = new File(url.getFile() + "Product.xml");

        ProductU product = (ProductU) unmarshaller.unmarshal(xml);

        List<ElementGenericType> productFields =  product.getFields().get(0).getProductField();
        System.out.println("Retrieving product fields... ");

        for(ElementGenericType field : productFields){
            System.out.println("name:"+ field.getName() + " || value: " + field.getValue());
        }

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setAdapter(adapter);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        System.out.println("");
        System.out.println("Marshalling...");
        ProductFieldU fieldU = new ProductFieldU();

        List<ElementGenericType> elementGenericTypes = new ArrayList<ElementGenericType>();
        ElementGenericType type = new ElementGenericType();
        type.setName("testName1");
        type.setValue("testValue1");
        elementGenericTypes.add(type);
        type = new ElementGenericType();
        type.setName("testName2");
        type.setValue("testValue2");
        elementGenericTypes.add(type);
        type = new ElementGenericType();
        type.setName("testName3");
        type.setValue(Integer.valueOf(12));
        elementGenericTypes.add(type);
        type = new ElementGenericType();
        type.setName("testName4");
        type.setValue("testValue4");
        elementGenericTypes.add(type);


        fieldU.setProductField(elementGenericTypes);
        ProductU newProduct = new ProductU();

        newProduct.setFields((List)ImmutableList.of(fieldU));


        marshaller.marshal(product, System.out);
    }

}