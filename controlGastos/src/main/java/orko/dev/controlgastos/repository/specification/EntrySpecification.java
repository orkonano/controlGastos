package orko.dev.controlgastos.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import orko.dev.controlgastos.model.Entry;
import orko.dev.controlgastos.model.Entry_;
import orko.dev.controlgastos.model.security.Principal;

public class EntrySpecification {
	
	
    public static Specification<Entry> userEquals(final Principal user) {
        
        return new Specification<Entry>() {
        	 @Override
             public Predicate toPredicate(Root<Entry> entryRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                 return cb.equal(entryRoot.get(Entry_.user), user);
             }
        };
    }
}
