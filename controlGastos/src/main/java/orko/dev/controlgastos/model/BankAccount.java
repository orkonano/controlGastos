package orko.dev.controlgastos.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import orko.dev.controlgastos.model.interfaces.Owneable;
import orko.dev.controlgastos.model.security.Principal;

@RooJavaBean
@RooToString
@RooJpaEntity
public class BankAccount implements Owneable<Principal>{
	
	@NotEmpty
	@NotNull
	private String description;
	
	@NotEmpty
	@NotNull
	private String bank;
	
	@Enumerated(EnumType.STRING)
    private BankAccountType bankAccountType;
	
	@ManyToOne
	private Principal user;
	
}
