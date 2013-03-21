package orko.dev.controlgastos.model.interfaces;

import java.math.BigDecimal;
import java.util.Date;

public interface BankOperation<T> extends Entity<T> {
	
	public Date getDate();
	public String getBankAccountDescription();
	public BigDecimal getAmount();

}
