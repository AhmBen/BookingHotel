package co.simplon.bookhotel.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
	@SequenceGenerator(name="customer_generator", sequenceName = "customer_sequence", allocationSize=1)
	@Column(name = "id")
	private Long id;
	
	@JsonIgnore
	@OneToOne(mappedBy = "customer",fetch=FetchType.EAGER)
	private Booking booking;
	
	@Column(name = "civility",nullable=false,length=1)
	private int civility;
	
	@Column(name = "name",nullable=false,length=50)
	private String name;
	
	@Column(name = "firstname",nullable=false,length=50)
	private String firstName;
	
	@Column(name = "dob",nullable=false)
	private LocalDate dob;
	
	@Column(name = "mail",nullable=false,length=50)
	private String mail;
	
	@Column(name = "address",nullable=false,length=50)
	private String address;
	
	@Column(name = "zipcode",nullable=false,length=5)
	private int zipcode;
	
	@Column(name = "city",nullable=false,length=50)
	private String city;
	
	@Column(name = "country",nullable=false,length=50)
	private String country;
	
	@Column(name = "phone",nullable=false,length=15)
	private String phone;
	
	@Column(name = "newsletter")
	private Boolean newsletter;

	public Customer() {
	}

	public Customer(int civility, String name, String firstname, LocalDate dob, String mail, String address, int zipcode,
			String city, String country, String phone, Boolean newsletter) {
		this(null,civility,name,firstname,dob,mail,address,zipcode,city,country,phone,newsletter);
		
	}
	
	public Customer(Long id, int civility, String name, String firstname, LocalDate dob, String mail, String address, int zipcode,
			String city, String country, String phone, Boolean newsletter) {
		this.id = id;
		this.civility = civility;
		this.name = name;
		this.firstName = firstname;
		this.dob = dob;
		this.mail = mail;
		this.address = address;
		this.zipcode = zipcode;
		this.city = city;
		this.country = country;
		this.phone = phone;
		this.newsletter = newsletter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public int getCivility() {
		return civility;
	}

	public void setCivility(int civility) {
		this.civility = civility;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getNewsletter() {
		return newsletter;
	}

	public void setNewsletter(Boolean newsletter) {
		this.newsletter = newsletter;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", booking=" + booking + ", civility=" + civility + ", name=" + name
				+ ", firstName=" + firstName + ", dob=" + dob + ", mail=" + mail + ", address=" + address + ", zipcode="
				+ zipcode + ", city=" + city + ", country=" + country + ", phone=" + phone + ", newsletter="
				+ newsletter + "]";
	}
	
	
}
