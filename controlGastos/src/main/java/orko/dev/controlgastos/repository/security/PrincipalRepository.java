package orko.dev.controlgastos.repository.security;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import orko.dev.controlgastos.model.security.Principal;

@RooJpaRepository(domainType = Principal.class)
public interface PrincipalRepository extends PrincipalRepositoryCustom {
	
	Principal getPrincipalByUsername(String username);
}
