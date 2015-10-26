package com.wmv.poc.javamail;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author wvergara, created on 10/8/15.
 */
public class GisFeTransformedPayloadMailer {

    // TODO: parameterized or  move to properties
    private static final String MSG_HDR_CONTENT_TYPE = "text/HTML; charset=UTF-8";
    private static final String MSG_HDR_FORMAT = "flowed";
    private static final String MSG_HDR_TRANSFER_ENCODING = "8bit";

    private static final String MSG_FROM_ADDR = "no_reply@dev.owens.com";

    JavaMailSender mailSender = null;

    public GisFeTransformedPayloadMailer() {
        mailSender = this.mailSender();
    }

    public static void main(String[] args) {
        GisFeTransformedPayloadMailer emailService = new GisFeTransformedPayloadMailer();
        emailService.send(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
    }

    private JavaMailSender mailSender() {
        final String host = "smtp.gmail.com";
        final String username = "devgtrsmtp@gmail.com";
        final String password = "y5m8@3075@c!1z52Kasf";
        final String port = "465";
        final String smtpAuth = "true";

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setHost(host);
        mailSender.setPort(Integer.valueOf(port));
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", Boolean.valueOf(smtpAuth));
        props.put("mail.smtp.port", port);
        mailSender.setJavaMailProperties(props);

        return mailSender;
    }

    private void send(String subject, String receipient, String gitBranch,  String miniCiVersion, String remarks, String xml, String filename) {
        final String sender = "gatordev@owens.com";
        final String senderPersonal = "no_reply@dev.owens.com";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            message.addHeader("Content-type", MSG_HDR_CONTENT_TYPE);
            message.addHeader("format", MSG_HDR_FORMAT);
            message.addHeader("Content-Transfer-Encoding", MSG_HDR_TRANSFER_ENCODING);

            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(new InternetAddress(sender, senderPersonal));
            messageHelper.setReplyTo(new InternetAddress(sender));
            messageHelper.setTo(InternetAddress.parse(receipient, false));
            messageHelper.setSubject(subject);


            String body = "<table style=\"margin:0;border:0;border-collapse:collapse;font-size:inherit;font-style:inherit;font-variant:inherit;font-weight:inherit;color:#333;overflow:hidden;padding-left:10em\">\n" +
                    "                                                <tbody>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <th style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;font-weight:bold;color:#707070;font-weight:normal;padding-bottom:10px;padding-right:10px;text-align:right;width:10em\">Server</th>\n" +
                    "                                                        <td style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;padding-bottom:10px;text-align:left\">{0}</td>\n" +
                    "                                                    </tr>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <th style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;font-weight:bold;color:#707070;font-weight:normal;padding-bottom:10px;padding-right:10px;text-align:right;width:10em\">Timestamp</th>\n" +
                    "                                                        <td style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;padding-bottom:10px;text-align:left\">{1}</td>\n" +
                    "                                                    </tr>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <th style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;font-weight:bold;color:#707070;font-weight:normal;padding-bottom:10px;padding-right:10px;text-align:right;width:10em\">Tool version</th>\n" +
                    "                                                        <td style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;padding-bottom:10px;text-align:left\">{2}</td>\n" +
                    "                                                    </tr>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <th style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;font-weight:bold;color:#707070;font-weight:normal;padding-bottom:10px;padding-right:10px;text-align:right;width:10em\">Remarks</th>\n" +
                    "                                                        <td style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;padding-bottom:10px;text-align:left\">{3}</td>\n" +
                    "                                                    </tr>\n" +
                    "                                                </tbody>\n" +
                    "                                            </table>";

            String[] arg = {
                    gitBranch,
                    new Date().toString(),
                    miniCiVersion,
                    remarks
            };
            String result = MessageFormat.format(
                    body,
                    arg);

            messageHelper.setText(result, true);
            messageHelper.setSentDate(new Date());


            try {
                xml = prettyPrint(xml);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            File temp = File.createTempFile("temp-file-name", ".csv");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(temp));
            bufferedWriter.write(xml);
            bufferedWriter.close();
            messageHelper.addAttachment(filename, temp);

            mailSender.send(message);
        } catch (IOException ioE) {
            ioE.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public static String prettyPrint(String xmlString) throws ParserConfigurationException, SAXException {
        try {
            Document doc = parse(xmlString);
            OutputFormat format = new OutputFormat(doc);
            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(2);
            Writer out = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(out, format);
            try {
                serializer.serialize(doc);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return out.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Document parse(String in) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(in));
        return builder.parse(is);
    }

}
