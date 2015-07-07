package com.owens.gis.poc.xsdgenerator.util;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;

public class ElementGenericTypeAdapter extends XmlAdapter<Element, ElementGenericType> {

    private ClassLoader classLoader;
    private DocumentBuilder documentBuilder;
    private JAXBContext jaxbContext;

    public ElementGenericTypeAdapter() {
        classLoader = Thread.currentThread().getContextClassLoader();
    }

    public ElementGenericTypeAdapter(JAXBContext jaxbContext) {
        this();
        this.jaxbContext = jaxbContext;
    }

    private DocumentBuilder getDocumentBuilder() throws Exception {
        // Lazy load the DocumentBuilder as it is not used for unmarshalling.
        if (null == documentBuilder) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            documentBuilder = dbf.newDocumentBuilder();
        }
        return documentBuilder;
    }

    private JAXBContext getJAXBContext(Class<?> type) throws Exception {
        if (null == jaxbContext) {
            return JAXBContext.newInstance(type);
        }
        return jaxbContext;
    }

    @Override
    public Element marshal(ElementGenericType elementGenericType) throws Exception {
        if (null == elementGenericType) {
            return null;
        }

        QName rootElement = new QName(elementGenericType.getName());
        Object value = elementGenericType.getValue();
        Class<?> type = value.getClass();
        JAXBElement jaxbElement = new JAXBElement(rootElement, type, value);

        Document document = getDocumentBuilder().newDocument();
        Marshaller marshaller = getJAXBContext(type).createMarshaller();
        marshaller.marshal(jaxbElement, document);
        Element element = document.getDocumentElement();


        // 3.  Set the type attribute based on the value's type.
        element.setAttribute("type", type.getName());

        return element;
    }

    @Override
    public ElementGenericType unmarshal(Element element) throws Exception {
        if (null == element) {
            return null;
        }
        // Determine the values type from the type attribute. If attribute is not specified. default to string
        String attrType = element.getAttribute("type");
        Class<?> type = classLoader.loadClass(StringUtils.isBlank(attrType)? "java.lang.String" :attrType);

        DOMSource source = new DOMSource(element);
        Unmarshaller unmarshaller = getJAXBContext(type).createUnmarshaller();

        // Unmarshal the element based on the value's type.
        JAXBElement jaxbElement = unmarshaller.unmarshal(source, type == null ? String.class : type);

        ElementGenericType elementGenericType = new ElementGenericType();
        elementGenericType.setName(element.getLocalName());
        elementGenericType.setValue(jaxbElement.getValue());
        return elementGenericType;

    }

}