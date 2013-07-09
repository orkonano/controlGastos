package orko.dev.controlgastos.email;


public interface NotificationService {
	
	public void sendMessage(String mailTo, String message);

	void sendMessage(String mailTo, String message, String subject);
}
