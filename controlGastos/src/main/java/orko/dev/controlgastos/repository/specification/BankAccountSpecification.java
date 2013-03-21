package orko.dev.controlgastos.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import orko.dev.controlgastos.model.BankAccount;
import orko.dev.controlgastos.model.BankAccount_;
import orko.dev.controlgastos.model.security.Principal;

public class BankAccountSpecification {

	public static Specification<BankAccount> userEquals(final Principal user) {

		return new Specification<BankAccount>() {
			@Override
			public Predicate toPredicate(Root<BankAccount> entryRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(entryRoot.get(BankAccount_.user), user);
			}
		};
	}

}
