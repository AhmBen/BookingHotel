package co.simplon.bookhotel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Data integrity violation")  // 400
public class BadRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
