package orko.dev.controlgastos.service.security;

import org.springframework.roo.addon.layers.service.RooService;

import orko.dev.controlgastos.model.security.Authority;

@RooService(domainTypes = { orko.dev.controlgastos.model.security.Authority.class })
public interface AuthorityService {

	Authority findDefaultRole();
}
