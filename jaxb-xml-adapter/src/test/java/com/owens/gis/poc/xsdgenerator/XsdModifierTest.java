package com.owens.gis.poc.xsdgenerator;

import com.owens.gis.poc.xsdgenerator.util.CustomSchemaOutputResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;

/**
 * @author wvergara, created on 6/30/15.
 */
public class XsdModifierTest {

    public static void main(String argv[]) throws Exception  {

        try {

            URL url = Thread.currentThread().getContextClassLoader().getResource("");
            String filepath = CustomSchemaOutputResolver.DEFAULT_FILENAME;

            File file = new File(url.getFile() + filepath);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            // mocks database data. Looping.
            String elementType = "<xs:complexType name=\"productField\">\n" +
                    "    <xs:sequence>\n" +
                    "      <xs:element name=\"product-id\"  type=\"xs:integer\"/>\n" +
                    "      <xs:element name=\"reference-order-id\" type=\"xs:string\"/>\n" +
                    "      <xs:element name=\"request-reason\" type=\"xs:string\"/>\n" +
                    "      <xs:element name=\"owens-username\" type=\"xs:string\"/>\n" +
                    "      <xs:element name=\"owens-username1\" type=\"xs:string\"/>\n" +
                    "      <xs:element name=\"owens-username2\" type=\"xs:string\"/>\n" +
                    "      <xs:element name=\"owens-username3\" type=\"xs:string\"/>\n" +
                    "      <xs:element name=\"owens-username4\" type=\"xs:string\"/>\n" +
                    "      <xs:element name=\"owens-username5\" type=\"xs:string\"/>\n" +
                    "    </xs:sequence>\n" +
                    "  </xs:complexType>";

            Element deleteElem = getNodeWithAttribute(doc, "name", "productField");
            doc.getDocumentElement().removeChild(deleteElem);
            DocumentBuilderFactory docFactory2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder2 = docFactory2.newDocumentBuilder();
            Document newDoc = docBuilder2.parse(new ByteArrayInputStream(elementType.getBytes()));

            Element newElem = doc.getDocumentElement();

            Node node = doc.importNode(newDoc.getDocumentElement(), true);
            newElem.appendChild(node);

            prettyPrint(doc);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            File fileoutput = new File(url.getFile() + "Modified"+filepath);

            StreamResult result = new StreamResult(fileoutput);
            transformer.transform(source, result);
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
