package com.wmv.poc;

import com.wmv.poc.jpa.poc.ProductU;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * @author wvergara, created on 6/30/15.
 */
public class JAXBUnmarshall {
    public static void main(String[] args) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ProductU.class);
            URL url = Thread.currentThread().getContextClassLoader().getResource("");

            File file = new File(url.getFile() + "product.xml");

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ProductU customer = (ProductU) jaxbUnmarshaller.unmarshal(file);
            System.out.println(customer);
            List<Element> productField = customer.getProductField();
            for (int i = 0; i < productField.size(); i++) {
                Node  node = productField.get(i).getFirstChild();
                do {
                    System.out.println("xxx:"+node.getLocalName());
                    System.out.println("yyy:"+node.getFirstChild().getNodeValue());
                    node = node.getNextSibling();
                } while (node.getFirstChild()!=null);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
