package co.simplon.bookhotel.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.simplon.bookhotel.exceptions.BadRequestException;
import co.simplon.bookhotel.model.Room;
import co.simplon.bookhotel.service.RoomService;

/**
 * Controller for Room
 * @author Ahmed BEN ROUAG
 *
 */

@Controller
@RequestMapping("/room")
public class RoomController {

	@Inject
	RoomService roomService;
	
	/**
	 * Find all Rooms
	 * @return list of all Rooms
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Room> findDistinctByRoomType() {
		return roomService.findDistinctByRoomType();
	}
	
  
	/**
	 * Find all free rooms for asked period
	 * @return List<Room>
	 * @throws BadRequestException 
	 */
	@RequestMapping(value = "/{get_datein}/{get_dateout}/{get_nbradults}/{get_nbrchildrens}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Room>> checkFreeRoom(	@PathVariable("get_datein") String d1, 
										@PathVariable("get_dateout") String d2,
										@PathVariable("get_nbradults") int nbradults,
										@PathVariable("get_nbrchildrens") int nbrchildrens) {	
										// @PathVariable("get_nbrchildrens") int nbrchildrens) throws BadRequestException {	
		
		List<Room> rooms = roomService.checkForm(d1, d2, nbradults, nbrchildrens);

		// Si on souhaite utiliser des Execptions perso
		// https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
		
		if (rooms == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);// throw new BadRequestException(); // Exception perso
		}
		else {
			return new ResponseEntity<>(rooms, HttpStatus.ACCEPTED);
		}
		// return rooms;
	}
	

}
