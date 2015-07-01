package com.wmv.poc.jpa.poc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author wvergara, created on 6/30/15.
 */
public class MyMapEntryType {
    @XmlAttribute
    public Integer key;

    @XmlValue
    public String value;
}
