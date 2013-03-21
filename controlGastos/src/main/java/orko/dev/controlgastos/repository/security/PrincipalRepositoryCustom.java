package orko.dev.controlgastos.repository.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import orko.dev.controlgastos.model.security.Principal;

public interface PrincipalRepositoryCustom {
	
	public Principal save(Principal principal);
	
	public void changePassword(Principal principal, String newPassword);
	
	public PasswordEncoder getPasswordEncoder();

}
