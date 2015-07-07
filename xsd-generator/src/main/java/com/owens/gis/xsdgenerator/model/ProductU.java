package com.owens.gis.xsdgenerator.model;

import org.w3c.dom.Element;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wvergara, created on 6/30/15.
 */
@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.FIELD)

public class ProductU {

    @XmlAnyElement()
    public List<Element> productField = new ArrayList<Element>();

    @XmlElement(
            name = "id",
            required = true
    )
    private String id;

    public String getId() {
        return id;
    }

    public List<Element> getProductField() {
        return productField;
    }

    public void setProductField(List<Element> productField) {
        this.productField = productField;
    }


}
