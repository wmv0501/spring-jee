package com.wmv.poc.javamail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author wvergara, created on 10/8/15.
 */
public class EmailService {

    // TODO: parameterized or  move to properties
    private static final String MSG_HDR_CONTENT_TYPE = "text/HTML; charset=UTF-8";
    private static final String MSG_HDR_FORMAT = "flowed";
    private static final String MSG_HDR_TRANSFER_ENCODING = "8bit";

    private static final String MSG_FROM_ADDR = "no_reply@dev.owens.com";

    JavaMailSender mailSender = null;

    public EmailService() {
        mailSender = this.mailSender();
    }

    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        emailService.send(args[0], args[1], args[2], args[3], args[4]);
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

    private void send(String receipient, String gitBranch, String gitVersion, String miniCiVersion, String remarks) {
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
            messageHelper.setSubject("GIS-DEV Server Update - [Unofficial]");


            String body = "<table style=\"margin:0;border:0;border-collapse:collapse;font-size:inherit;font-style:inherit;font-variant:inherit;font-weight:inherit;color:#333;overflow:hidden;padding-left:10em\">\n" +
                    "                                                <tbody>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <th style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;font-weight:bold;color:#707070;font-weight:normal;padding-bottom:10px;padding-right:10px;text-align:right;width:10em\">Git branch</th>\n" +
                    "                                                        <td style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;padding-bottom:10px;text-align:left\">{0}</td>\n" +
                    "                                                    </tr>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <th style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;font-weight:bold;color:#707070;font-weight:normal;padding-bottom:10px;padding-right:10px;text-align:right;width:10em\">Git version</th>\n" +
                    "                                                        <td style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;padding-bottom:10px;text-align:left\">{1}</td>\n" +
                    "                                                    </tr>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <th style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;font-weight:bold;color:#707070;font-weight:normal;padding-bottom:10px;padding-right:10px;text-align:right;width:10em\">Completed</th>\n" +
                    "                                                        <td style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;padding-bottom:10px;text-align:left\">{2}</td>\n" +
                    "                                                    </tr>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <th style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;font-weight:bold;color:#707070;font-weight:normal;padding-bottom:10px;padding-right:10px;text-align:right;width:10em\">Deployer Version</th>\n" +
                    "                                                        <td style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;padding-bottom:10px;text-align:left\">{3}</td>\n" +
                    "                                                    </tr>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <th style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;font-weight:bold;color:#707070;font-weight:normal;padding-bottom:10px;padding-right:10px;text-align:right;width:10em\">Remarks</th>\n" +
                    "                                                        <td style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;padding-bottom:10px;text-align:left\">{4}</td>\n" +
                    "                                                    </tr>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <td style=\"font:13px Arial,FreeSans,Helvetica,sans-serif;text-align:left;vertical-align:top;padding-bottom:10px;text-align:left\"><a href=\"http://ci.app.owens.dev:8085/deploy/viewDeploymentVersionCommits.action?deploymentProjectId=18972674&amp;versionId=25985033&amp;compareWithVersionId=25985031\" style=\"color:#326ca6;text-decoration:none\" target=\"_blank\"></a></td>\n" +
                    "                                                    </tr>\n" +
                    "                                                </tbody>\n" +
                    "                                            </table>";

            String[] arg = {
                    gitBranch,
                    gitVersion,
                    new Date().toString(),
                    miniCiVersion,
                    remarks
            };
            String result = MessageFormat.format(
                    body,
                    arg);

            messageHelper.setText(result, true);
            messageHelper.setSentDate(new Date());
            mailSender.send(message);
        } catch (IOException ioE) {
            ioE.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
