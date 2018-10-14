package co.simplon.bookhotel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "roomtype")
public class RoomType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roomtype_generator")
	@SequenceGenerator(name="roomtype_generator", sequenceName = "roomtype_sequence", allocationSize=1)
	@Column(name = "id")
	private Long id;
	
	@OneToMany(mappedBy = "roomType",fetch=FetchType.LAZY)
	private List<Room> room = new ArrayList<>();
	
	@Column(name = "type",nullable=false,length=50)
	private String type;

	@Column(name = "ppn",nullable=false) /*Price Per Night*/
	private Double ppn;
	
	@Column(name = "nbradults",nullable=false)
	private int nbradults;
	
	@Column(name = "nbrchildrens",nullable=false)
	private int nbrchildrens;
	
	@Column(name = "surface",nullable=false)
	private int surface;
	
	@Column(name = "description",nullable=false,length=255)
	private String description;

	public RoomType() {		
	}

	public RoomType(String type, Double ppn, int nbradults, int nbrchildrens, int surface, String description) {
		this(null,type,ppn,nbradults,nbrchildrens,surface,description);
	}
	
	public RoomType(Long id, String type, Double ppn, int nbradults, int nbrchildrens, int surface, String description) {
		super();
		this.id = id;
		this.type = type;
		this.ppn = ppn;
		this.nbradults = nbradults;
		this.nbrchildrens = nbrchildrens;
		this.surface = surface;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPpn() {
		return ppn;
	}

	public void setPpn(Double ppn) {
		this.ppn = ppn;
	}

	public int getNbradults() {
		return nbradults;
	}

	public void setNbradults(int nbradults) {
		this.nbradults = nbradults;
	}

	public int getNbrchildrens() {
		return nbrchildrens;
	}

	public void setNbrchildrens(int nbrchildrens) {
		this.nbrchildrens = nbrchildrens;
	}

	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RoomType [id=" + id + ", type=" + type + ", ppn=" + ppn + ", nbradults=" + nbradults + ", nbrchildrens="
				+ nbrchildrens + ", surface=" + surface + "]";
	}

}
