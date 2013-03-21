package orko.dev.controlgastos.repository.security;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import orko.dev.controlgastos.model.security.Authority;

@RooJpaRepository(domainType = Authority.class)
public interface AuthorityRepository {

	Authority findAuthorityByAuthority(String defaultRole);
}
