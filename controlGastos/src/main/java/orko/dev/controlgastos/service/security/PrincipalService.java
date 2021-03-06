package orko.dev.controlgastos.service.security;

import org.springframework.roo.addon.layers.service.RooService;

import orko.dev.controlgastos.model.security.Principal;
import orko.dev.controlgastos.model.security.form.PrincipalChangePassword;
import orko.dev.controlgastos.structs.MasiveMail;

@RooService(domainTypes = { orko.dev.controlgastos.model.security.Principal.class })
public interface PrincipalService {
	
	public Principal getPrincipalLogin();
	
	public void changePassword(PrincipalChangePassword principal);

	public boolean isValidActualPassword(String oldPassword);

	void enviarMailAllUser(MasiveMail masiveMail);
}
