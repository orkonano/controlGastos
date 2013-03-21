package orko.dev.controlgastos.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import orko.dev.controlgastos.model.BudgetEntry;
import orko.dev.controlgastos.model.BudgetEntry_;
import orko.dev.controlgastos.model.security.Principal;

public class BudgetEntrySpecification {

	public static Specification<BudgetEntry> userEquals(final Principal user) {

		return new Specification<BudgetEntry>() {
			@Override
			public Predicate toPredicate(Root<BudgetEntry> entryRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(entryRoot.get(BudgetEntry_.user), user);
			}
		};
	}

}
