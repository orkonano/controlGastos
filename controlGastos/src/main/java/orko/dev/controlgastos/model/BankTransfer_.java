package orko.dev.controlgastos.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import orko.dev.controlgastos.model.security.Principal;


@StaticMetamodel(BankTransfer.class)
public class BankTransfer_ {

	public static volatile SingularAttribute<BankTransfer, Principal> user;
	
}
