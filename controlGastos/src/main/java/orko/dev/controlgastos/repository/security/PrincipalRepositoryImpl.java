package orko.dev.controlgastos.repository.security;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

import orko.dev.controlgastos.model.security.Principal;

public class PrincipalRepositoryImpl extends JdbcDaoImpl implements PrincipalRepositoryCustom {
	
	private PasswordEncoder passwordEncoder;
	@PersistenceContext
	private EntityManager em;

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Principal save(Principal principal) {
		principal.setPassword(this.passwordEncoder.encode(principal.getPassword()));
		this.em.persist(principal);
		return principal;
	}

	@Override
	public void changePassword(Principal principal, String newPassword) {
		principal.setPassword(this.passwordEncoder.encode(newPassword));
		this.em.merge(principal);
	}

}
