package co.simplon.bookhotel.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfig {
	
	/*
	 * Get params values for mail from mail.properties
	 */
	@Value("${mail.password}")
	private String mailPassword;
	
	@Value("${mail.from}")
	private String mailFrom;
	
	@Value("${mail.smtp.host}")
	private String mailSmtpHost;
	
	@Value("${mail.smtp.port}")
	private int mailSmtpPort;
	
	@Value("${mail.smtp.auth}")
	private Boolean mailSmtpAuth;
	
	@Value("${mail.smtp.starttls.enable}")
	private Boolean mailSmtpStarttlsEnable;
	
	private final String rootSendingMail = "src/main/resources/sendingEmail/";
	
	@Bean
    public MailSender mailSender() {
      JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

      Properties props = new Properties();
      props.put("mail.smtp.auth", mailSmtpAuth);//Outgoing server requires authentication
      props.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);//TLS must be activated
      
      mailSender.setJavaMailProperties(props);

      mailSender.setUsername(mailFrom);
      mailSender.setPassword(mailPassword);//change with your sender email password
      mailSender.setHost(mailSmtpHost); //Outgoing smtp server - change it to your SMTP server
      mailSender.setPort(mailSmtpPort);//Outgoing port
      return mailSender;
	}
	
	@Bean
	  public SimpleMailMessage defaultMessage() {
	      SimpleMailMessage smm = new SimpleMailMessage();
	      smm.setTo("ahmed.benrouag@gmail.com");
	      smm.setFrom(mailFrom);
	      smm.setSubject("Default subject");
	      smm.setText("Default text");
	      return smm;
	  }

	public String getRootSendingMail() {
		return rootSendingMail;
	}
	
	public String getMailPassword() {
		return mailPassword;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public String getMailSmtpHost() {
		return mailSmtpHost;
	}

	public int getMailSmtpPort() {
		return mailSmtpPort;
	}


	public Boolean getMailSmtpAuth() {
		return mailSmtpAuth;
	}

	public Boolean getMailSmtpStarttlsEnable() {
		return mailSmtpStarttlsEnable;
	}

	

	
	
/*	
    @Autowired
    Environment env;

    @Bean
    public boolean test() {
       //  System.out.println(env.getProperty("mail.from"));
        return true;
    }
 */
}