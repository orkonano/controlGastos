package orko.dev.controlgastos.repository;

import java.util.Collection;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import orko.dev.controlgastos.model.Entry;
import orko.dev.controlgastos.model.security.Principal;

@RooJpaRepository(domainType = Entry.class)
public interface EntryRepository extends CustomBasicUserRepository<Entry>{

	Collection<Entry> findEntryByAttributableAndUser(Boolean attributable, Principal user);
	
}
