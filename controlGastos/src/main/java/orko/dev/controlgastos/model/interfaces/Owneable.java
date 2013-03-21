package orko.dev.controlgastos.model.interfaces;

import orko.dev.controlgastos.model.security.Principal;

public interface Owneable<T extends Principal> {

	public T getUser();
}
