package orko.dev.controlgastos.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import orko.dev.controlgastos.model.security.Principal;


@StaticMetamodel(EconomicFact.class)
public class EconomicFact_ {

	public static volatile SingularAttribute<EconomicFact, Principal> user;
	
}
