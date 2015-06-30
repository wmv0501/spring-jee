package org.springframework.security.samples.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @author wvergara, created on 6/11/15.
 */
public class MessagePropertyTest {

    @org.junit.Test
    public void test() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource("config.properties");
            input = new FileInputStream(url.getFile());
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            System.out.println(prop.getProperty("database"));
            System.out.println(prop.getProperty("dbuser"));
            System.out.println(prop.getProperty("dbpassword"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
