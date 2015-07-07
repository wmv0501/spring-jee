package com.owens.gis.xsdgenerator.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author wvergara, created on 6/30/15.
 */
@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.FIELD)

public class Product {

    @XmlElement(
            name = "fields",
            required = true
    )
    private List<ProductField> productField;
    @XmlElement(
            name = "id",
            required = true
    )
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProductField> getProductField() {
        return productField;
    }

    public void setProductField(List<ProductField> productField) {
        this.productField = productField;
    }


}
