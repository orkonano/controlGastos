package orko.dev.controlgastos.service.security;

import org.springframework.roo.addon.layers.service.RooService;

import orko.dev.controlgastos.model.security.Principal;

@RooService(domainTypes = { orko.dev.controlgastos.model.security.AuthorityPrincipalAssignment.class })
public interface AuthorityPrincipalAssignmentService {

	void saveDefaultRole(Principal principal);
}
