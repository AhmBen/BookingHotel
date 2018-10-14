package co.simplon.bookhotel.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import co.simplon.bookhotel.model.Room;
import co.simplon.bookhotel.repository.RoomRepository;
import co.simplon.bookhotel.service.RoomService;

@Named
public class RoomServiceImpl implements RoomService{
	
	@Inject
	RoomRepository roomRepository;

	@Inject
	CommonServiceImpl commonService;
	
	
	@Override
	public List<Room> findDistinctByRoomType() {
		return roomRepository.findDistinctByRoomType();
	}
	
	
	@Override
	public List<Room> checkFreeRoom(LocalDate datein,LocalDate dateout,int nbradults,int nbrchildrens) {
		return roomRepository.checkFreeRoom(datein,dateout,nbradults,nbrchildrens);
	}
	
	
	@Override
	public List<Room> checkForm(String dIn,String dOut,int nbradults,int nbrchildrens) {
		
		Boolean formOk = true;
		
        /*
		 * Ressources for Date :		 
		 * - https://stackoverflow.com/questions/29927362/localtime-from-date
		 * - https://stackoverflow.com/questions/46276174/conversion-to-java-sql-date
		 * - https://www.foreach.be/blog/java-8-date-and-time
		 * - https://www.baeldung.com/java-period-duration
		 */        
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.FRANCE);
        LocalDate datein = LocalDate.parse(dIn, formatter);
        LocalDate dateout = LocalDate.parse(dOut, formatter);        
		Period period = Period.between(datein, dateout); // Number of days between 2 dates
		
		if(!commonService.isValidDate(dIn)) 	{	formOk = false;	}        
        if(!commonService.isValidDate(dOut)) 	{	formOk = false;	}              
        if (period.getDays() <= 0)			 	{	formOk = false;	}            
        if (nbradults < 1) 						{	formOk = false;	}              
        if (nbrchildrens < 0) 					{	formOk = false;	}      
    
        return formOk ? checkFreeRoom(datein,dateout,nbradults,nbrchildrens) : null; 
        
	}
	
}
