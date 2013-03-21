package orko.dev.controlgastos.repository;

import java.math.BigDecimal;

import orko.dev.controlgastos.model.BankAccount;

public interface BankTransferRepositoryCustom {
	
	BigDecimal getBalanceByBankAccount(BankAccount bankAccount);

}
