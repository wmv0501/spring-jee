package com.wmv.poc.jpa.poc;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author wvergara, created on 6/30/15.
 */
public final class ItemMapAdapter extends XmlAdapter<LinkedList<String>, HashMap<String, String>>
{
    @Override
    public LinkedList<String> marshal(HashMap<String, String> v) {
        return null;
    }

    @Override
    public HashMap<String, String> unmarshal(LinkedList<String> v) {
        return null;
    }
}