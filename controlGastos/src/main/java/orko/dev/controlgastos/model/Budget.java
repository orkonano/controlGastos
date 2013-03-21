package orko.dev.controlgastos.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import orko.dev.controlgastos.model.interfaces.Owneable;
import orko.dev.controlgastos.model.security.Principal;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Budget implements Owneable<Principal>{

	@NotNull
	@NotEmpty
	@Size(max = 200)
	@Column(unique=true)
	private String description;

	@OneToMany(mappedBy = "budget")
	private Set<BudgetEntry> budgetEntries;
	
	@ManyToOne
	private Principal user;


}
