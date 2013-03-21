package orko.dev.controlgastos.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import orko.dev.controlgastos.model.Budget;
import orko.dev.controlgastos.model.Budget_;
import orko.dev.controlgastos.model.security.Principal;

public class BudgetSpecification {
	
	
    public static Specification<Budget> userEquals(final Principal user) {
        
        return new Specification<Budget>() {
        	 @Override
             public Predicate toPredicate(Root<Budget> entryRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                 return cb.equal(entryRoot.get(Budget_.user), user);
             }
        };
    }
}
