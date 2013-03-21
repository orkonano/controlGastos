package orko.dev.controlgastos.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import orko.dev.controlgastos.model.interfaces.BankOperation;
import orko.dev.controlgastos.model.interfaces.Owneable;
import orko.dev.controlgastos.model.security.Principal;

@RooJavaBean
@RooToString
@RooJpaEntity
public class EconomicFact implements Owneable<Principal>,BankOperation<Long>{

    @Override
	public String getBankAccountDescription() {
		return this.description;
	}

	@ManyToOne
    @NotNull
    private Entry entry;
    
    @NotEmpty
    @NotNull
    private String description;
    
    @ManyToOne
    @NotNull
    private Budget budget;
    
    @NotNull
	@DecimalMin("0.0")
	@Digits(integer=5,fraction=2)
    private BigDecimal amount;
    
    @NotNull
    @DateTimeFormat(style="S-")
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @NotNull
    @ManyToOne
    private BankAccount bankAccount;
    
    @ManyToOne
	private Principal user;
    
    @AssertTrue(message="No se puede imputar en un Entry qye no es imputable")
    @Transient
    public boolean isValidEntry(){
    	return this.entry.getAttributable();
    }
}
