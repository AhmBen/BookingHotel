package co.simplon.bookhotel.service;

import javax.inject.Named;

import co.simplon.bookhotel.model.ContactMail;

@Named
public interface CommonService {
	public Boolean isValidDate(String date);
	public Boolean isValidEmail(String email);
	public Boolean sendContactMail(ContactMail contactMail);
}
