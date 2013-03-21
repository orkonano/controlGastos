package orko.dev.controlgastos.service;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import orko.dev.controlgastos.model.BankAccount;
import orko.dev.controlgastos.model.BankTransfer;

@RooService(domainTypes = { orko.dev.controlgastos.model.BankTransfer.class })
public interface BankTransferService {

	List<BankTransfer> findLastBankTransfer( BankAccount bankAccount);
}
