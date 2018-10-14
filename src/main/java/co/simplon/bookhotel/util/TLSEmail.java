package co.simplon.bookhotel.util;

import java.util.Properties;

import javax.inject.Named;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import co.simplon.bookhotel.config.MailConfig;
import co.simplon.bookhotel.model.Booking;

@Named
public class TLSEmail {

	
	public static  void sendAttachmentEmail(Booking booking, MailConfig mailConfig) {


		final String fromEmail 	= mailConfig.getMailFrom(); 		// requires valid gmail id
		final String password 	= mailConfig.getMailPassword(); 	// correct password for gmail id
		final String toEmail 	= booking.getCustomer().getMail(); 	// customer's email 
		
		System.out.println("TLSEmail Start");
		
		Properties props = new Properties();
		props.put("mail.smtp.host"				, mailConfig.getMailSmtpHost()); 			//SMTP Host
		props.put("mail.smtp.port"				, mailConfig.getMailSmtpPort()); 			//TLS Port 
		props.put("mail.smtp.auth"				, mailConfig.getMailSmtpAuth()); 			//true => enable authentication
		props.put("mail.smtp.starttls.enable"	, mailConfig.getMailSmtpStarttlsEnable()); //true => enable STARTTLS
		
		//create Authenticator object to pass in Session.getInstance argument

		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		
		Session session = Session.getInstance(props, auth);
		
		EmailUtil.sendAttachmentEmail(	session, 
										toEmail, 
										mailConfig.getRootSendingMail(),
										booking.getNumBooking(),
										"Confirmation de réservation : " + booking.getNumBooking(), 
										"<h1>H1 Title</h1><strong>Message1</strong><br />Message2");
		
	}
	
	// https://www.journaldev.com/2532/javamail-example-send-mail-in-java-smtp#send-email-in-java-smtp-with-tls-authentication
	/*
	public static void sendMail(Booking booking, String mailFrom, String mailPassword, String mailSmtpHost, 
								int mailSmtpPort, Boolean mailSmtpAuth, Boolean mailSmtpStarttlsEnable) {
	*/	
		public static void sendMail(Booking booking) {
		
			
		/*
		final String fromEmail 	= mailFrom; 						//requires valid gmail id
		final String password 	= mailPassword; 					// correct password for gmail id
		final String toEmail 	= booking.getCustomer().getMail(); 	// can be any email id 
		
		System.out.println("TLSEmail Start");
		
		Properties props = new Properties();
		props.put("mail.smtp.host"				, mailSmtpHost); 			//SMTP Host
		props.put("mail.smtp.port"				, mailSmtpPort); 			//TLS Port 
		props.put("mail.smtp.auth"				, mailSmtpAuth); 			//true => enable authentication
		props.put("mail.smtp.starttls.enable"	, mailSmtpStarttlsEnable); //true => enable STARTTLS
		
        
		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		
		Session session = Session.getInstance(props, auth);
		
		EmailUtil.sendEmail(session, toEmail, "Confirmation de réservation : " + booking.getNumBooking(), "<h1>H1 Title</h1><strong>Message1</strong><br />Message2");
		*/
		
	}

	
}

