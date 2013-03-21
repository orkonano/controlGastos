package orko.dev.controlgastos.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import orko.dev.controlgastos.model.BankTransfer;
import orko.dev.controlgastos.model.BankTransfer_;
import orko.dev.controlgastos.model.security.Principal;

public class BankTransferSpecification {

	public static Specification<BankTransfer> userEquals(final Principal user) {

		return new Specification<BankTransfer>() {
			@Override
			public Predicate toPredicate(Root<BankTransfer> entryRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(entryRoot.get(BankTransfer_.user), user);
			}
		};
	}

}
