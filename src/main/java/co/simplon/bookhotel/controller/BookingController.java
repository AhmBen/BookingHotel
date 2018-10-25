package co.simplon.bookhotel.controller;

import java.io.IOException;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.simplon.bookhotel.model.Booking;
import co.simplon.bookhotel.service.BookingService;
import co.simplon.bookhotel.util.TLSEmail;
import freemarker.template.TemplateException;

/**
 * Controller for Booking
 * @author Ahmed BEN ROUAG
 *
 */

@Controller
@RequestMapping("/booking")
public class BookingController {
	
	@Inject
	BookingService bookingService;

	/*
	 * Get params values for mail from mail.properties
	 */
	/*
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
*/

	/*
	 * true 	: Send Email
	 * false 	: Don't send mail => Only for test
	 */
	private Boolean sendMail = true;
	
	/**
	 * Find all Booking
	 * @return List<Booking>
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody	
	public List<Booking> findAll() {			
		return bookingService.findAll();
	}
	
	/**
	 * Generate a Booking Number
	 * @return String
	 */
	@RequestMapping(value = "/generateNewBookingNumber", method=RequestMethod.GET)
	@ResponseBody
	public String generateBookingNumber() {
		return bookingService.generateBookingNumber();
	}
	
	/**
	 * Create a new Booking and a new Customer
	 * @return ResponseEntity<Booking>
	 * @throws IOException 
	 * @throws TemplateException 
	 * @RequestBody Booking
	 */
	@RequestMapping(value = "/create", method=RequestMethod.POST) 
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Booking> createBookingAndCustomer(@RequestBody Booking booking) throws TemplateException, IOException {

		if (!bookingService.checkCreateForm(booking)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else {
		
			// Generate Booking number
			booking.setNumBooking(bookingService.generateBookingNumber());
			
			// Calcul number of nights
			Period period = Period.between(booking.getDateIn(), booking.getDateOut());
			booking.setNbrNights(period.getDays());

			// Set Name to Uppercase
			booking.getCustomer().setName(booking.getCustomer().getName().toUpperCase());
			
			// Create a new Customer
			Long idCustomer = bookingService.createCustomer(booking.getCustomer());
			booking.getCustomer().setId(idCustomer);
			
			// Create a new Booking
			booking.setId(bookingService.createBooking(booking));
			
			// Generate PDF, a voir par la suite
			// https://www.baeldung.com/java-pdf-creation		
	
			// Send a mail to the customer with the booking summary
			bookingService.sendAttachmentEmail(booking);
			
			return new ResponseEntity<>(booking, HttpStatus.ACCEPTED);
		}
		
	}		
	/**
	 * Find a specific Booking
	 * @return Optional<Booking> 
	 */
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Optional<Booking> findById(@PathVariable("id") Long id) {
		return bookingService.findById(id);
	}
	
	/**
	 * Display a specific Booking (For security, control with the name of the customer)
	 * @return Boolean
	 */
	@RequestMapping(value = "/display/{numBooking}/{name}", method=RequestMethod.GET)
	@ResponseBody
	public Booking findByNumBookingAndName(@PathVariable("numBooking") String numBooking, @PathVariable("name") String name) {
		return bookingService.findByNumBookingAndName(numBooking,name);
	}
	
	/**
	 * Delete a specific Booking (Control with the name of the customer)
	 * @return Boolean
	 */
	@RequestMapping(value = "/delete/{idBooking}/{name}", method=RequestMethod.DELETE)
	@ResponseBody
	public Boolean findByIdAndName(@PathVariable("idBooking") Long id, @PathVariable("name") String name) {
		return bookingService.delBooking(id,name);
	}
	
}
