package orko.dev.controlgastos.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import orko.dev.controlgastos.model.security.Principal;


@StaticMetamodel(Entry.class)
public class Entry_ {

	public static volatile SingularAttribute<Entry, Principal> user;
	
}
