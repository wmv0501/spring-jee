package com.wmv.poc.jpa.poc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * @author wvergara, created on 6/30/15.
 */

//@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name="fields")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductFieldU {

    @XmlElement(
            name = "product-id",
            required = true
    )
    private Map<String, String> value;

}


