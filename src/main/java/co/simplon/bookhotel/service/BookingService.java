package co.simplon.bookhotel.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;

import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;

import co.simplon.bookhotel.model.Booking;
import co.simplon.bookhotel.model.Customer;
// import co.simplon.bookhotel.model.Room;
// import co.simplon.bookhotel.model.RoomType;
import freemarker.template.TemplateException;

@Named
public interface BookingService {
	public Optional<Booking> findById(Long id);
	public List<Booking> findAll();	
	public String generateBookingNumber();
	public Boolean delBooking(Long id, String name);
	public Long createCustomer(Customer customer);
	public Long createBooking(Booking booking);
	public Booking findByNumBookingAndName(String numBooking, String name);
	public Boolean checkCreateForm(Booking booking);
	public void sendAttachmentEmail(Booking booking) throws TemplateException, IOException;
}

