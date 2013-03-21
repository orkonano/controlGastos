package orko.dev.controlgastos.repository;

import java.math.BigDecimal;

import orko.dev.controlgastos.model.BankAccount;

public interface EconomicFactRepositoryCustom {
	
	BigDecimal getBalanceByBankAccount(BankAccount bankAccount);

}
