package orko.dev.controlgastos.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import orko.dev.controlgastos.model.Entry;
import orko.dev.controlgastos.model.security.Principal;
import orko.dev.controlgastos.repository.specification.EntrySpecification;
import orko.dev.controlgastos.service.security.PrincipalService;


public class EntryServiceImpl implements EntryService {
	
	@Autowired
	private PrincipalService principalService;

	@Override
	public Collection<Entry> findEntryByAttributable(Boolean attributable) {
		Principal userLogin = this.principalService.getPrincipalLogin();
		return this.entryRepository.findEntryByAttributableAndUser(attributable,userLogin);
	}


	public void saveEntry(Entry entry) {
		entry.setUser(this.principalService.getPrincipalLogin());
		entryRepository.save(entry);
    }

	@Transactional(readOnly=true)
	public List<Entry> findEntryEntries(int firstResult, int maxResults) {
		Principal userLogin = this.principalService.getPrincipalLogin();
        return entryRepository.findAllByUser(userLogin,new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	@Transactional(readOnly=true)
	public List<Entry> findAllEntrys() {
		Principal userLogin = this.principalService.getPrincipalLogin();
        return entryRepository.findAllByUser(userLogin);
    }

	
	@Transactional(readOnly=true)
	public long countAllEntrys() {
		Principal userLogin = this.principalService.getPrincipalLogin();
        return entryRepository.count(EntrySpecification.userEquals(userLogin));
    }

	public Entry updateEntry(Entry entry) {
		entry.setUser(this.principalService.getPrincipalLogin());
        return entryRepository.save(entry);
    }
}
