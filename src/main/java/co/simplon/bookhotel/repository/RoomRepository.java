package co.simplon.bookhotel.repository;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.simplon.bookhotel.model.Room;

@Named
public interface RoomRepository extends JpaRepository<Room, Long>{
	/*
	 * List of all free rooms for asked period
	 * 
	 * On recupere la liste de toutes les chambres reservées
	 * On recupere la liste de toutes les chambres non reservées pour la periode données grace au NOT IN 
	 * On recupere la liste de toutes les chambres non reservées pour la periode données par type de chambre
	 *  - Jointure avec sous requete ne fonctionne pas avec jpql, voir l'url en exemple.
	 * 
	 * Example : https://stackoverflow.com/questions/51891198/jpa-query-join-a-subquery-with-grouping-condition
	 */

	@Query(value = "SELECT r3 FROM Room r3 WHERE r3.id = " // Get one type of room if many of the same type
			+ "("
			// ----- Start Sub Request 2 : Looking for id of all free rooms pay attention number of personnes -----
			+ "SELECT min(r1.id) as id FROM Room r1 WHERE r1 NOT IN  "	
			+ "("
			// ----- Start Sub Request 1 : Looking for id of all booking rooms -----
				+ "SELECT b.room  FROM Booking b "  //room dans model Room (mappedBy) et non pas fk_idroom de Booking
				+ "WHERE "
					+ "("
						+ " (datein <= :datein AND dateout >= :dateout)"
						+ " OR "
						+ " (datein >  :datein AND datein  <  :dateout)"
						+ " OR "
						+ " (dateout > :datein AND dateout <  :dateout)"
					+ ") "
			// ----- End Sub Request 1 -----
			+ ") AND (r1.roomType.nbradults + r1.roomType.nbrchildrens) >= (:nbradults + :nbrchildrens)"
			// ----- End Sub Request 2 -----
			+ " AND r1.roomType.id = r3.roomType.id)"
	)	
	public List<Room> checkFreeRoom(@Param("datein") LocalDate datein, 
									@Param("dateout") LocalDate dateout,
									@Param("nbradults") int nbradults,
									@Param("nbrchildrens") int nbrchildrens);
	

	@Query(value = "SELECT r FROM Room r WHERE r.id IN "
				+  "("
				+  "	SELECT min(r1.id) as id FROM Room r1 WHERE r1.roomType.id IN  "	
				+  "	("
				+ "			SELECT distinct(r2.roomType.id) FROM Room r2"
				+ "		)"
				+ "		GROUP BY r1.roomType.id "
				+  ")"
			)
	public List<Room> findDistinctByRoomType();
}
