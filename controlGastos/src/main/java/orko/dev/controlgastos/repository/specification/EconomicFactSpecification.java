package orko.dev.controlgastos.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import orko.dev.controlgastos.model.EconomicFact;
import orko.dev.controlgastos.model.EconomicFact_;
import orko.dev.controlgastos.model.security.Principal;

public class EconomicFactSpecification {

	public static Specification<EconomicFact> userEquals(final Principal user) {

		return new Specification<EconomicFact>() {
			@Override
			public Predicate toPredicate(Root<EconomicFact> entryRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(entryRoot.get(EconomicFact_.user), user);
			}
		};
	}

}
