package co.simplon.bookhotel.repository;

import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.simplon.bookhotel.model.Booking;

@Named
public interface BookingRepository  extends JpaRepository<Booking, Long>{
	
	@Query(value = "SELECT b FROM Booking b WHERE b.id = :idBooking AND b.customer.name = :name")
	public Booking findBookingAndCustomer(@Param("idBooking") Long idBooking,
			 						  	  @Param("name") String name);
	
	@Query(value = "SELECT b FROM Booking b WHERE b.numBooking = :numBooking AND b.customer.name = :name")
	public Booking findByNumBookingAndName( @Param("numBooking") String numBooking,
		  	  								@Param("name") String name);
	
}
