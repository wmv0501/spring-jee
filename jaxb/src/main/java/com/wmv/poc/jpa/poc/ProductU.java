package com.wmv.poc.jpa.poc;

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

   /* @XmlElement(
            name = "fields",
            required = true
    )
    @XmlJavaTypeAdapter(MyMapAdapter.class)*/

//    private Map<String, String> productField;


    @XmlAnyElement()
    public List<Element> productField = new ArrayList<Element>();

    @XmlAttribute
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
