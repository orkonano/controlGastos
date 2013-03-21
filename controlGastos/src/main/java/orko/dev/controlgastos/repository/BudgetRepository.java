package orko.dev.controlgastos.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import orko.dev.controlgastos.model.Budget;
import orko.dev.controlgastos.model.security.Principal;

@RooJpaRepository(domainType = Budget.class)
public interface BudgetRepository extends CustomBasicUserRepository<Budget> {

	List<Budget> findAllByUser(Principal principalLogin, Sort sort);
	
	Budget findBudgetByDescriptionAndUser(String description,Principal user);
	
	Budget findBudgetByDescriptionAndUserAndIdNot(String description, Principal user,Long id);

}
