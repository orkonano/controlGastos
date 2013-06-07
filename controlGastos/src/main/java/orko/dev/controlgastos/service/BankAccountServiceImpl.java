package orko.dev.controlgastos.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import orko.dev.controlgastos.model.BankAccount;
import orko.dev.controlgastos.model.interfaces.BankOperation;
import orko.dev.controlgastos.repository.BankTransferRepository;
import orko.dev.controlgastos.repository.EconomicFactRepository;
import orko.dev.controlgastos.repository.specification.BankAccountSpecification;
import orko.dev.controlgastos.service.security.PrincipalService;


public class BankAccountServiceImpl implements BankAccountService {
	
	@Autowired
	private PrincipalService principalService;
	
	@Autowired
	private EconomicFactRepository economicFactRepository;
	
	@Autowired
	private BankTransferRepository bankTranferRepository;
	
	@Autowired
	private EconomicFactService economicFactService;
	
	@Autowired
	private BankTransferService bankTransferService;

	@Override
	@Transactional(readOnly = true)
	public BigDecimal getBalance(BankAccount bankAccount) {
		BigDecimal balance =  this.economicFactRepository.getBalanceByBankAccount(bankAccount);
		balance = balance.add(this.bankTranferRepository.getBalanceByBankAccount(bankAccount));
		return balance;
	}
	

	public void saveBankAccount(BankAccount bankAccount) {
		bankAccount.setUser(this.principalService.getPrincipalLogin());
        bankAccountRepository.save(bankAccount);
    }

	@Transactional(readOnly = true)
	public List<BankAccount> findAllBankAccounts() {
        return bankAccountRepository.findAllByUser(this.principalService.getPrincipalLogin());
    }

	@Transactional(readOnly = true)
	public List<BankAccount> findBankAccountEntries(int firstResult, int maxResults) {
        return bankAccountRepository.findAllByUser(this.principalService.getPrincipalLogin(),new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	@Transactional(readOnly = true)
	public long countAllBankAccounts() {
        return bankAccountRepository.count(BankAccountSpecification.userEquals(this.principalService.getPrincipalLogin()));
    }

	public BankAccount updateBankAccount(BankAccount bankAccount) {
		bankAccount.setUser(this.principalService.getPrincipalLogin());
        return bankAccountRepository.save(bankAccount);
    }


	@Override
	public List<BankOperation> findLastBankOperation(BankAccount bankAccount) {
		List<BankOperation> listReturn = new ArrayList<BankOperation> ();
		listReturn.addAll(this.economicFactService.findLastEconomicFacts(bankAccount));
		listReturn.addAll(this.bankTransferService.findLastBankTransfer(bankAccount));
		Collections.sort(listReturn, new Comparator<BankOperation>(){

			@Override
			public int compare(BankOperation arg0, BankOperation arg1) {
				return arg0.getDate().compareTo(arg1.getDate())*-1;
			}
			
		});
		return listReturn.size()<20?listReturn: listReturn.subList(0, 20);
	}
}
