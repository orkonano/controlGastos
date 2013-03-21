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
public class BankTransfer implements Owneable<Principal>,BankOperation<Long> {

	@NotEmpty
	@NotNull
	private String description;

	@Override
	public String getBankAccountDescription() {
		return new StringBuilder("Dest: ").append(this.bankAccountDest.getDescription()).append(", Desc").append(this.getDescription()).toString();
	}

	@NotNull
	@DecimalMin("0.0")
	@Digits(integer = 5, fraction = 2)
	private BigDecimal amount;

	@NotNull
	@DateTimeFormat(style = "S-")
	@Temporal(TemporalType.DATE)
	private Date date;

	@NotNull
	@ManyToOne
	private BankAccount bankAccountDest;

	@NotNull
	@ManyToOne
	private BankAccount bankAccountSrc;

	@ManyToOne
	private Principal user;

	@AssertTrue(message = "La cuenta bancaria destino debe ser diferente a la origen.")
	@Transient
	public boolean isValidTransfer() {
		return !this.bankAccountDest.equals(this.bankAccountSrc);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankTransfer other = (BankTransfer) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	public boolean isIngress(BankAccount baOrigen) {
		return this.bankAccountDest.equals(baOrigen);
	}
	
	
}
