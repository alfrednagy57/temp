/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginandreg;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author 20102
 */
public class ValMail {
    public static boolean EmailVal(String input)
    {
        String EmailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat  = Pattern.compile(EmailRegex,Pattern.CASE_INSENSITIVE);
        Matcher matcher   = emailPat.matcher(input);
        return matcher.find();
    }
    
    public static boolean SendMail(String input)
    {
        final String UserName="alfred";
        final String Password=new String();
        
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // Trust Gmail's certificate
        properties.put("mail.smtp.ssl.enable", "true"); // Enable SSL/TLS
        
        
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(UserName,Password);
			}
        });
        
        MimeMessage msg = new MimeMessage(session);

        try {
			msg.setFrom(new InternetAddress("ALfred.nagy16@gmail.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("ALfred.nagy18@gmail.com"));
			msg.setSubject("Subject Line");
			
			Multipart emailContent = new MimeMultipart();
			
			//Text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText("My multipart text");
			
			//Attachment body part.
			MimeBodyPart pdfAttachment = new MimeBodyPart();
			pdfAttachment.attachFile("/home/parallels/Documents/docs/javamail.pdf");
			
			//Attach body parts
			emailContent.addBodyPart(textBodyPart);
			emailContent.addBodyPart(pdfAttachment);
			
			//Attach multipart to message
			msg.setContent(emailContent);
			
			Transport.send(msg);
			System.out.println("Sent message");
                        
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
        // TODO Auto-generated catch block
        return true;
    }
}
