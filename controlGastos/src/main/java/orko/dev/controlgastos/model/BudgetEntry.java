package orko.dev.controlgastos.model;

import java.math.BigDecimal;

import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import orko.dev.controlgastos.model.interfaces.Owneable;
import orko.dev.controlgastos.model.security.Principal;


@RooJavaBean
@RooToString
@RooJpaEntity
public class BudgetEntry implements Owneable<Principal>, Cloneable{
	
	@NotNull
	@DecimalMin("0.0")
	@Digits(integer=5,fraction=2)
	private BigDecimal amount;
	
	@ManyToOne
	@NotNull
	private Entry entry;
	
	@ManyToOne
	@NotNull
	private Budget budget;
	
	@ManyToOne
	private Principal user;
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		BudgetEntry budgetEntryCloned = new BudgetEntry();
		budgetEntryCloned.setAmount(this.getAmount());
		budgetEntryCloned.setBudget(this.getBudget());
		budgetEntryCloned.setEntry(this.getEntry());
		budgetEntryCloned.setUser(this.getUser());
		return budgetEntryCloned;
	}

}
