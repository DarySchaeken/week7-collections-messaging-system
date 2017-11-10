package be.pxl.collections.messagingsystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
	private final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/mm/yyyy");			
	private String subject;
	private String content;
	
	public Message(String subject, String content) throws MessageSystemException {
		if(subject == null || subject.equals("")){
			MessageSystemException messageSystemException = new MessageSystemException(ValidationCode.MISSING_REQUIRED_FIELD);
			messageSystemException.addField("missing_field", "subject");
			throw messageSystemException;
		}
		if(content == null || content.equals("")){
			MessageSystemException messageSystemException = new MessageSystemException(ValidationCode.MISSING_REQUIRED_FIELD);
			messageSystemException.addField("missing_field", "content");
			throw messageSystemException;
		}
		
		
		this.subject = subject;
		this.content = content;
	}

	public String getContent(Follower follower) {
		content = content.replace("%NICK%", follower.getNick());
		content = content.replace("%NOW%", LocalDateTime.now().format(DATE_FORMAT));
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

}
