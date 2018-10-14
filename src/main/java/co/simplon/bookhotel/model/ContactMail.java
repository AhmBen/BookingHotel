package co.simplon.bookhotel.model;

public class ContactMail {

	private String name;
	private String firstName;
	private String email;
	private String subject;
	private String message;
	
	private ContactMail contactMail;

	public ContactMail() {
	}
	
	public ContactMail(String name, String firstName, String email, String subject, String message) {
		this.name = name;
		this.firstName = firstName;
		this.email = email;
		this.subject = subject;
		this.message = message;
	}

	public ContactMail(ContactMail contactMail) {
		this.contactMail = contactMail;
	}
	
	public ContactMail getContactMail() {
		return contactMail;
	}

	public void setContactMail(ContactMail contactMail) {
		this.contactMail = contactMail;
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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ContactMail [name=" + name + ", firstName=" + firstName + ", email=" + email + ", subject=" + subject
				+ ", message=" + message + "]";
	}

	
}
