package co.simplon.bookhotel.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {


	/**
	 * Utility method to send email with attachment
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public static void sendAttachmentEmail(Session session, String toEmail, String rootSendingMail, String fileName, String subject, String body){
		
		System.out.println("EmailUtil Start");
		
		final String fn = fileName + ".html";
		
		try{
	         MimeMessage msg = new MimeMessage(session);
	         msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		     msg.addHeader("format", "flowed");
		     msg.addHeader("Content-Transfer-Encoding", "8bit");
		      
		     msg.setFrom(new InternetAddress(toEmail, "Hotel Fictif"));

		     msg.setReplyTo(InternetAddress.parse(toEmail, false));

		     msg.setSubject(subject, "UTF-8");

		     msg.setSentDate(new Date());

		     msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

		    
	         // Create the message body part
	         // BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         // messageBodyPart.setText(body);

		     
		     BufferedInputStream bis;
		     String str = "";
		     int i = 0;
		     
			 try {
					bis = new BufferedInputStream(new FileInputStream(new File(rootSendingMail + fn)));
					
				     try {
						while((i = bis.read()) != -1) {
						     str += (char)i;
						 }
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
			 }
		     
		     
	         MimeBodyPart messageBodyPart = new MimeBodyPart();
	         // messageBodyPart.setContent(body, "text/html");
	         messageBodyPart.setContent(str, "text/html");
	         
	         // Create a multipart message for attachment
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);
		     
	         // Second part is attachment
	         messageBodyPart = new MimeBodyPart();
	        //  String filename = "output.html";
	         DataSource source = new FileDataSource(rootSendingMail + fn);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(fn);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         msg.setContent(multipart);

	         // Send message
	         Transport.send(msg);
	         System.out.println("EMail Sent Successfully with attachment!!");
	      }catch (MessagingException e) {
	         e.printStackTrace();
	      } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		}
	}
	
	/**
	 * Utility method to send simple HTML email
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public static Boolean sendEmail(Session session, String toEmail, String subject, String body){
		try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("bookinghotelproject@gmail.com", "NoReply-JD"));

	      msg.setReplyTo(InternetAddress.parse("bookinghotelproject@gmail.com", false));

	      msg.setSubject(subject, "UTF-8");

	      // msg.setText(body, "UTF-8");
	      msg.setContent(body, "text/html");

	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      System.out.println("Message is ready");
	      
	      
	      // Create the message body part
	      //BodyPart messageBodyPart = new MimeBodyPart();
	      
	      // Create a multipart message for attachment
	      //Multipart multipart = new MimeMultipart();	      
	      
	      //third part for displaying image in the email body
	      //messageBodyPart = new MimeBodyPart();
	      //messageBodyPart.setContent(body, "text/html");
	      //multipart.addBodyPart(messageBodyPart);
         
	      //Set the multipart message to the email message
	      //msg.setContent(multipart);
	      
    	  Transport.send(msg);  
    	  
	      System.out.println("EMail Sent Successfully to :" + toEmail);
	      
	      return true;
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      return false;
	    }
	}
	
}
