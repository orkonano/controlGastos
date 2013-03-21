package orko.dev.controlgastos.structs;

import java.math.BigDecimal;

import orko.dev.controlgastos.model.EconomicFact;

public class EconomicFactBankHistorial {
	
	private EconomicFact economicFact;
	private BigDecimal balance;
	
	public EconomicFact getEconomicFact() {
		return economicFact;
	}
	public void setEconomicFact(EconomicFact economicFact) {
		this.economicFact = economicFact;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
