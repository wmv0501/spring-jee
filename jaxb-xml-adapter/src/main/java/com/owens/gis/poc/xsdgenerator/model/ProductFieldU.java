package com.owens.gis.poc.xsdgenerator.model;

import com.owens.gis.poc.xsdgenerator.util.ElementGenericType;
import com.owens.gis.poc.xsdgenerator.util.ElementGenericTypeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import java.util.Map;

/**
 * @author wvergara, created on 6/30/15.
 */

//@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name="fields")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductFieldU {



    @XmlAnyElement
    @XmlJavaTypeAdapter(ElementGenericTypeAdapter.class)
    private List<ElementGenericType> elementGenericTypeList;

    public List<ElementGenericType> getProductField() {
        return elementGenericTypeList;
    }

    public void setProductField(List<ElementGenericType> productField) {
        this.elementGenericTypeList = productField;
    }

}


