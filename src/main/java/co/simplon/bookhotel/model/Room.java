package co.simplon.bookhotel.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "room")
public class Room {	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rooms_generator")
	@SequenceGenerator(name="rooms_generator", sequenceName = "rooms_sequence", allocationSize=1)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fk_roomtype", nullable=false)
	private RoomType roomType;
	
	@JsonIgnore
	@OneToMany(mappedBy = "room",fetch=FetchType.EAGER)
	private List<Booking> booking;

	public Room() {
	}

	public Room(RoomType roomType) {
		this(null,roomType);
	}
	
	public Room(Long id, RoomType roomType) {
		this.id = id;
		this.roomType = roomType;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public List<Booking> getBooking() {
		return booking;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", roomType=" + roomType + ", booking=" + booking + "]";
	}
	
}
