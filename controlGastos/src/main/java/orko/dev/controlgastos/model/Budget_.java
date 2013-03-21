package orko.dev.controlgastos.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import orko.dev.controlgastos.model.security.Principal;


@StaticMetamodel(Budget.class)
public class Budget_ {

	public static volatile SingularAttribute<Budget, Principal> user;
	
}
