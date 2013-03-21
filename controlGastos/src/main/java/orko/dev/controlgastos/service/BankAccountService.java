package orko.dev.controlgastos.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import orko.dev.controlgastos.model.BankAccount;
import orko.dev.controlgastos.model.interfaces.BankOperation;

@RooService(domainTypes = { orko.dev.controlgastos.model.BankAccount.class })
public interface BankAccountService {

	BigDecimal getBalance(BankAccount bankAccount);

	List<BankOperation> findLastBankOperation(BankAccount bankAccount);

}
