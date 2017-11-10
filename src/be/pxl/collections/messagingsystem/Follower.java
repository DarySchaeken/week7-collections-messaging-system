package be.pxl.collections.messagingsystem;

import java.time.LocalDate;

public class Follower {
	private String nick;
	private String email;
	private String city;
	private LocalDate dateOfBirth;

	public Follower(String nick, String email) throws MessageSystemException {
		if (nick == null || nick.equals("")) {
			MessageSystemException messageSystemException = new MessageSystemException(ValidationCode.MISSING_REQUIRED_FIELD);
			messageSystemException.addField("missing_field", "nick");
			throw messageSystemException;
		}
		if (email == null || email.equals("")) {
			MessageSystemException messageSystemException = new MessageSystemException(ValidationCode.MISSING_REQUIRED_FIELD);
			messageSystemException.addField("missing_field", "email");
			throw messageSystemException;
		}
		this.nick = nick;
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNick() {
		return nick;
	}

	public String getEmail() {
		return email;
	}

	public String toString() {
		return nick;
	}

	public int compareTo(Follower f) {
		if(this.nick.equals(f.nick) || this.email.equals(f.email)){
			return 0;
		} 
		return -1;
	}

}
