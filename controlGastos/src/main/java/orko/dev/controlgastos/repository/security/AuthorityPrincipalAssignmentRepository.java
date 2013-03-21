package orko.dev.controlgastos.repository.security;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import orko.dev.controlgastos.model.security.AuthorityPrincipalAssignment;

@RooJpaRepository(domainType = AuthorityPrincipalAssignment.class)
public interface AuthorityPrincipalAssignmentRepository {
}
