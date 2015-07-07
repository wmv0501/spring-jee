package com.owens.gis.poc.xsdgenerator.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author wvergara, created on 6/30/15.
 */
@XmlRootElement(name = "product")
public class ProductU {

    private String id;

    @XmlElement
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private List<ProductFieldU> fields;


    @XmlElement
    public List<ProductFieldU> getFields() {
        return fields;
    }

    public void setFields(List<ProductFieldU> fields) {
        this.fields = fields;
    }


}

