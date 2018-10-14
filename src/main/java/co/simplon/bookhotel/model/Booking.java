package co.simplon.bookhotel.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_generator")
	@SequenceGenerator(name="booking_generator", sequenceName = "booking_sequence", allocationSize=1)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "numbooking",nullable=false,length=6)
	private String numBooking;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fk_idcustomer", nullable=false)
    private Customer customer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fk_idroom", nullable=false)
    private Room room;

	@Column(name = "datein",nullable=false)
	private LocalDate dateIn;
	
	@Column(name = "dateout",nullable=false)
	private LocalDate dateOut;
	
	@Column(name = "nbrnights",nullable=false,length=3)
	private int nbrNights;
	
	@Column(name = "nbradults",nullable=false)
	private int nbrAdults;
	
	@Column(name = "nbrchildrens",nullable=false)
	private int nbrChildrens;
	
	@Column(name = "breakfast")
	private Boolean breakfast;

	public Booking() {
	}

	public Booking(String numbooking, Customer customer, Room room, LocalDate datein, LocalDate dateout, int nbrnights, int nbradults,int nbrchildrens, Boolean breakfast) {
		this(null,numbooking,customer,room,datein,dateout,nbrnights,nbradults,nbrchildrens,breakfast);
	}
	
	public Booking(Long id, String numbooking, Customer customer, Room room, LocalDate datein, LocalDate dateout, int nbrnights, int nbradults,int nbrchildrens, Boolean breakfast) {
		this.id = id;
		this.numBooking = numbooking;
		this.customer = customer;
		this.room = room;
		this.dateIn = datein;
		this.dateOut = dateout;
		this.nbrNights = nbrnights;
		this.nbrAdults = nbradults;
		this.nbrChildrens = nbrchildrens;
		this.breakfast = breakfast;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumBooking() {
		return numBooking;
	}

	public void setNumBooking(String numBooking) {
		this.numBooking = numBooking;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public LocalDate getDateIn() {
		return dateIn;
	}

	public void setDateIn(LocalDate dateIn) {
		this.dateIn = dateIn;
	}

	public LocalDate getDateOut() {
		return dateOut;
	}

	public void setDateOut(LocalDate dateOut) {
		this.dateOut = dateOut;
	}

	public int getNbrNights() {
		return nbrNights;
	}

	public void setNbrNights(int nbrNights) {
		this.nbrNights = nbrNights;
	}

	public int getNbrAdults() {
		return nbrAdults;
	}

	public void setNbrAdults(int nbrAdults) {
		this.nbrAdults = nbrAdults;
	}

	public int getNbrChildrens() {
		return nbrChildrens;
	}

	public void setNbrChildrens(int nbrChildrens) {
		this.nbrChildrens = nbrChildrens;
	}

	public Boolean getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(Boolean breakfast) {
		this.breakfast = breakfast;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", numBooking=" + numBooking + ", customer=" + customer + ", room=" + room
				+ ", dateIn=" + dateIn + ", dateOut=" + dateOut + ", nbrNights=" + nbrNights + ", nbrAdults="
				+ nbrAdults + ", nbrChildrens=" + nbrChildrens + ", breakfast=" + breakfast + "]";
	}

	
}
