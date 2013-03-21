package orko.dev.controlgastos.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import orko.dev.controlgastos.model.security.Principal;


@StaticMetamodel(BankAccount.class)
public class BankAccount_ {

	public static volatile SingularAttribute<BankAccount, Principal> user;
	
}
