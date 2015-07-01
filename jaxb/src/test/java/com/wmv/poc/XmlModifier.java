package com.wmv.poc;
import java.io.*;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * @author wvergara, created on 6/30/15.
 */
public class XmlModifier {

    public static void main(String argv[]) throws Exception  {

        try {

            URL url = Thread.currentThread().getContextClassLoader().getResource("");
            String filepath = "product02.xml";

            File file = new File(url.getFile() + filepath);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            // mocks database data. Looping.
            String elementType = "<xs:complexType name=\"productField\">\n" +
                    "    <xs:sequence>\n" +
                    "      <xs:element name=\"product-id\" required=true type=\"xs:integer\"/>\n" +
                    "      <xs:element name=\"reference-order-id\" type=\"xs:string\"/>\n" +
                    "      <xs:element name=\"request-reason\" type=\"xs:string\"/>\n" +
                    "      <xs:element name=\"owens-username\" type=\"xs:string\"/>\n" +
                    "      <xs:element name=\"owens-username\" type=\"xs:string\"/>\n" +
                    "      <xs:element name=\"owens-username\" type=\"xs:string\"/>\n" +
                    "      <xs:element name=\"owens-username\" type=\"xs:string\"/>\n" +
                    "      <xs:element name=\"owens-username\" type=\"xs:string\"/>\n" +
                    "      <xs:element name=\"owens-username\" type=\"xs:string\"/>\n" +
                    "    </xs:sequence>\n" +
                    "  </xs:complexType>";
//            NodeList list = doc.getElementsByTagName("xs:schema");
//            Node node = doc.createTextNode(elementType);
            Element deleteElem = getNodeWithAttribute(doc, "name", "productField");
            doc.getDocumentElement().removeChild(deleteElem);
            DocumentBuilderFactory docFactory2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder2 = docFactory2.newDocumentBuilder();
            Document newDoc = docBuilder2.parse(new ByteArrayInputStream(elementType.getBytes()));

            Element newElem = doc.getDocumentElement();
//            doc.appendChild(newElem);

            Node node = doc.importNode(newDoc.getDocumentElement(), true);
            newElem.appendChild(node);

            prettyPrint(doc);

/*
            // Get the staff element , it may not working if tag has spaces, or
            // whatever weird characters in front...it's better to use
            // getElementsByTagName() to get it directly.
            // Node staff = company.getFirstChild();

            // Get the staff element by tag name directly
            Node staff = doc.getElementsByTagName("staff").item(0);

            // update staff attribute
            NamedNodeMap attr = staff.getAttributes();
            Node nodeAttr = attr.getNamedItem("id");
            nodeAttr.setTextContent("2");

            // append a new node to staff
            Element age = doc.createElement("age");
            age.appendChild(doc.createTextNode("28"));
            staff.appendChild(age);

            // loop the staff child node
            NodeList list = staff.getChildNodes();

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                // get the salary element, and update the value
                if ("salary".equals(node.getNodeName())) {
                    node.setTextContent("2000000");
                }

                //remove firstname
                if ("firstname".equals(node.getNodeName())) {
                    staff.removeChild(node);
                }

            }
*/
            // write the content into xml file
           /* TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);
*/
            System.out.println("Done");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }
    }

    public static final void prettyPrint(Document xml) throws Exception {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(xml), new StreamResult(out));
        System.out.println(out.toString());
    }

    public static Element getNodeWithAttribute(Node root, String attrName, String attrValue)
    {
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            if (n instanceof Element) {
                Element el = (Element) n;
                if (el.getAttribute(attrName).equals(attrValue)) {
                    return el;
                }else{
                    el =  getNodeWithAttribute(n, attrName, attrValue); //search recursively
                    if(el != null){
                        return el;
                    }
                }
            }
        }
        return null;
    }

}
