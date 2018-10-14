package co.simplon.bookhotel.service.impl;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Named;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import co.simplon.bookhotel.config.MailConfig;
import co.simplon.bookhotel.model.ContactMail;
import co.simplon.bookhotel.service.CommonService;
import co.simplon.bookhotel.util.EmailUtil;

@Named
public class CommonServiceImpl implements CommonService {

	@Inject
    MailConfig mailConfig;  
	
	@Override
	public Boolean isValidDate(String date) {
		
		Boolean dateOk = true;
		String dateFormat = "yyyy-MM-dd";
  
		if(date == null) {			  
			return false;
		}
  
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		format.setLenient(false);
  
		try {
			format.parse(date.trim());
		} catch (ParseException pe) {
			// pe.printStackTrace();
			// return false;
			dateOk = false;
		}
  
		// return true;
		return dateOk;
	}

	@Override
	public Boolean isValidEmail(String email) { 

        String emailRegex = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$";
        
        Pattern pat = Pattern.compile(emailRegex); 
        
        return (email == null) ? false :  pat.matcher(email).matches();
    } 
	
	
	@Override
	public Boolean sendContactMail(ContactMail contactMail) {
		System.out.println(contactMail);

		final String fromEmail 	= mailConfig.getMailFrom(); 		// requires valid gmail id
		final String password 	= mailConfig.getMailPassword(); 	// correct password for gmail id
		
		final String toEmail 	= mailConfig.getMailFrom();			// Send to Admin Hotel, not to then sender.	
		
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
		
		return EmailUtil.sendEmail(
									session, 
									toEmail, 
									"Message reçu sur Hotel Fictif",
									"SUJET : " 	 + contactMail.getSubject() 	+ " <br />" +
									"NOM : " 	 + contactMail.getName() 		+ " <br />" + 
									"PRENOM : "  + contactMail.getFirstName() 	+ " <br />" +
									"EMAIL : " 	 + contactMail.getEmail() 		+ " <br />" +
									"MESSAGE : " + contactMail.getMessage()
								  );
		

	};


}
