package orko.dev.controlgastos.service.security;

import org.springframework.transaction.annotation.Transactional;

import orko.dev.controlgastos.model.security.Authority;


public class AuthorityServiceImpl implements AuthorityService {
	
	private static final String DEFAULT_ROLE = "ROLE_USER";

	@Override
	@Transactional(readOnly=true)
	public Authority findDefaultRole() {
		return this.authorityRepository.findAuthorityByAuthority(DEFAULT_ROLE);
	}
}
