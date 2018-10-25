package co.simplon.bookhotel.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.SecureRandom;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import co.simplon.bookhotel.config.MailConfig;
import co.simplon.bookhotel.model.Booking;
import co.simplon.bookhotel.model.Customer;
import co.simplon.bookhotel.repository.BookingRepository;
import co.simplon.bookhotel.repository.CustomerRepository;
import co.simplon.bookhotel.service.BookingService;
import co.simplon.bookhotel.util.TLSEmail;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

@Named
public class BookingServiceImpl implements BookingService {
	
	@Inject
	BookingRepository bookingRepository;
	
	@Inject
	CustomerRepository customerRepository;
	
	@Inject
	CommonServiceImpl commonService;
	
    @Inject
    MailConfig mailConfig;    
	
	@Override
	public void sendAttachmentEmail(Booking booking) throws TemplateException, IOException {
		
		Configuration cfg = new Configuration(); 
		// Where do we load the templates from:
        cfg.setClassForTemplateLoading(Booking.class, "templates");
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates/"));

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setOutputEncoding("UTF-8");
        cfg.setLocale(Locale.FRANCE);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        
        // 2. Proccess template(s)
        //
        // You will do this for several times in typical applications.

        // 2.1. Prepare the template input:

        Map<String, Object> input = new HashMap<String, Object>();
        /*
        input.put("civility", booking.getCustomer().getCivility() == 1 ? "Monsieur" : "Madame");
        input.put("name", booking.getCustomer().getName());
        input.put("numBooking", booking.getNumBooking());        
        input.put("nbrAdults", booking.getNbrAdults());
        input.put("nbrChildrens", booking.getNbrChildrens());
        input.put("nbrNights", booking.getNbrNights());
        input.put("typeOfRoom", booking.getRoom().getRoomType().getType());
        input.put("totalPrice", booking.getRoom().getRoomType().getPpn() * booking.getNbrNights());
        */
        input.put("dateIn"	, DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRANCE).format(booking.getDateIn()));
        input.put("dateOut"	, DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRANCE).format(booking.getDateOut()));
        input.put("booking"	, booking);

        // List<Booking> systems = new ArrayList<Booking>();
        // systems.add(booking);
        // input.put("systems", systems);
        
     // 2.2. Get the template

        Template template = cfg.getTemplate("emailBookingSummary.html");
                
        // 2.3. Generate the output

        // Write output to the console
        /*
        Writer consoleWriter = new OutputStreamWriter(System.out);
        template.process(input, consoleWriter);
		*/
        
        
        // For the sake of example, also write output into a file:
        // Writer fileWriter = new FileWriter(new File("output.html"));
        // Writer fileWriter = new FileWriter(new File(mailConfig.getRootSendingMail() + "output.html"));
        Writer fileWriter = new FileWriter(new File(mailConfig.getRootSendingMail() + booking.getNumBooking() + ".html"));
        try {
        	template.setEncoding("UTF-8");
            template.setOutputEncoding("UTF-8");
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }
        
        TLSEmail.sendAttachmentEmail(booking, mailConfig);
        
		
	}
	
	@Override
	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}
	
	@Override
	public Optional<Booking> findById(Long id) {
		return bookingRepository.findById(id);
	}
	
	@Override
	public Boolean checkCreateForm(Booking booking) {
		
		Boolean formOk = true;
		
        Period period = Period.between(booking.getDateIn(), booking.getDateOut());
        
        // Check all the fields from Create Booking/Customer
        // All the values tested are the same like front.        
        
        // Check If Valid Date In
        if(!commonService.isValidDate(String.valueOf(booking.getDateIn()))) 									{  	formOk = false;     }		
        
        // Check If Valid Date Out
        if(!commonService.isValidDate(String.valueOf(booking.getDateOut())))			 						{  	formOk = false;     }		
        
        // Check NbrNights
		if (period.getDays() <= 0)																				{  	formOk = false;     }
		
		// Check NbrAdults
        if (booking.getNbrAdults() < 1) 																		{  	formOk = false;     }
        
        // Check NbrChildrens
        if (booking.getNbrChildrens() < 0) 																		{  	formOk = false;     }
        
        // Check Civility
        if (booking.getCustomer().getCivility() != 1 && booking.getCustomer().getCivility() != 2)     			{  	formOk = false;     }        
        
        // Check Name Length
        if (booking.getCustomer().getName().length() < 2)      													{	formOk = false;		}
        
        // Check FirstName Length
        if (booking.getCustomer().getFirstName().length() < 2)      											{	formOk = false;		}
        
        // Check Phone Length
        if (booking.getCustomer().getPhone().length() < 8 || booking.getCustomer().getPhone().length() > 15 )	{	formOk = false;		}
        
        // Check If Valid Email
        if (!commonService.isValidEmail(booking.getCustomer().getMail()))										{	formOk = false;		}
       
        // Check Address Length
        if (booking.getCustomer().getAddress().length() < 5)      												{	formOk = false;		}
        
        // Check Zip Length
        if (String.valueOf( booking.getCustomer().getZipcode() ).length() != 5)									{	formOk = false;		}
        
        // Check City Length
        if (booking.getCustomer().getCity().length() < 2)      													{	formOk = false;		}
        
        // Check Country Length
        if (booking.getCustomer().getCountry().length() < 2)      												{	formOk = false;		}        
        
        return formOk;
	}
		
	@Override
	public Boolean delBooking(Long id, String name) {
		
		Boolean deleteStatus = false;

		Booking booking = bookingRepository.findBookingAndCustomer(id,name.toUpperCase());
		
		if (booking != null) {
			
			//Delete the Booking (mandatory the first)
			bookingRepository.deleteById(id);
			
			//Delete the Customer. 
			//The delete must be after the Booking's delete because there is a constraint
			customerRepository.deleteById(booking.getCustomer().getId());	
			
			deleteStatus = true;
		}
		
		return deleteStatus;
	}
	
	@Override
	public String generateBookingNumber() {		
		int lengthOfBookingNumber = 6;
		String charset = "123456789ABCDEFGHIJKLMNPQRSTUVWXYZ"; //Without O and 0
		int charsetLength = charset.length();

		String generatedBookingNumber = ""; 
		SecureRandom secureRandom = new SecureRandom();

		for (int i=0; i<lengthOfBookingNumber; i++) {
			generatedBookingNumber += charset.charAt(secureRandom.nextInt(charsetLength)); 
		}
		
		return generatedBookingNumber;
	}	
	
	@Override
	public Booking findByNumBookingAndName(String numBooking, String name) {
		return bookingRepository.findByNumBookingAndName(numBooking,name.toUpperCase());		
	}
	
	@Override
	public Long createCustomer(Customer customer) {
		return customerRepository.save(customer).getId();
	}
	
	@Override
	public Long createBooking(Booking booking) {
		return bookingRepository.save(booking).getId();		
	}


}
