package orko.dev.controlgastos.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import orko.dev.controlgastos.model.security.Authority;
import orko.dev.controlgastos.model.security.AuthorityPrincipalAssignment;
import orko.dev.controlgastos.model.security.Principal;


public class AuthorityPrincipalAssignmentServiceImpl implements AuthorityPrincipalAssignmentService {
	
	@Autowired
	private AuthorityService roleService;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveDefaultRole(Principal principal) {
		Authority defaultRole = roleService.findDefaultRole();
		AuthorityPrincipalAssignment apa = new AuthorityPrincipalAssignment();
		apa.setUsername(principal);
		apa.setRoleId(defaultRole);
		this.saveAuthorityPrincipalAssignment(apa);
	}
}
