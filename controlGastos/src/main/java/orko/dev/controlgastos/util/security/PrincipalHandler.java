package orko.dev.controlgastos.util.security;

import org.springframework.security.core.userdetails.User;

public interface PrincipalHandler {
	
	public User getUser();

}
