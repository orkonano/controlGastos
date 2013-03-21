package orko.dev.controlgastos.service;

import java.util.Collection;

import org.springframework.roo.addon.layers.service.RooService;

import orko.dev.controlgastos.model.Entry;

@RooService(domainTypes = { orko.dev.controlgastos.model.Entry.class })
public interface EntryService {

	Collection<Entry> findEntryByAttributable(Boolean attributable);
}
