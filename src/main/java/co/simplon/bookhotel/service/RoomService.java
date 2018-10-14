package co.simplon.bookhotel.service;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Named;

import co.simplon.bookhotel.model.Room;


@Named
public interface RoomService {
	// find all Rooms
	public List<Room> findDistinctByRoomType();
	public List<Room> checkFreeRoom(LocalDate datein,LocalDate dateout,int nbradults,int nbrchildrens);
	public List<Room> checkForm(String datein,String dateout,int nbradults,int nbrchildrens);
}
