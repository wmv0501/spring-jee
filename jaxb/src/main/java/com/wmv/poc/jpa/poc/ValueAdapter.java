package com.wmv.poc.jpa.poc;

import org.w3c.dom.Element;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author wvergara, created on 6/30/15.
 */
public class ValueAdapter extends XmlAdapter<String, Object> {



    @Override
    public String unmarshal(String v) throws Exception {
        return null;
    }

    @Override
    public String marshal(Object v) throws Exception {
        Element element = (Element) v;
        return element.getTextContent();
    }
}
