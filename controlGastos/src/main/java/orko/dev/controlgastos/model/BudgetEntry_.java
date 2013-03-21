package orko.dev.controlgastos.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import orko.dev.controlgastos.model.security.Principal;


@StaticMetamodel(BudgetEntry.class)
public class BudgetEntry_ {

	public static volatile SingularAttribute<BudgetEntry, Principal> user;
	
}
