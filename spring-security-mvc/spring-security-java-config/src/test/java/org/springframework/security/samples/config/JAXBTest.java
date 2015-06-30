package org.springframework.security.samples.config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.FileOutputStream;

/**
 * @author wvergara, created on 6/11/15.
 */
public class JAXBTest {
    @XmlRootElement
    static class SomePojo{

        String name;
        String surname;

        public String getName() {
            return name;
        }
        @XmlElement

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }
        @XmlElement

        public void setSurname(String surname) {
            this.surname = surname;
        }
    }


    public static void main(String args[]) {

        try {
            SomePojo somePojo = new SomePojo();
            somePojo.setName("Somename");
            somePojo.setSurname("SomeSurname");
            JAXBContext context = JAXBContext.newInstance(SomePojo.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(somePojo, new FileOutputStream("/Users/wvergara/_Development/_temp/somepojo.xml"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
