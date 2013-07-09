package orko.dev.controlgastos.structs;

import org.hibernate.validator.constraints.NotEmpty;

public class MasiveMail {

	@NotEmpty
	private String subject;
	@NotEmpty
	private String body;
	
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
