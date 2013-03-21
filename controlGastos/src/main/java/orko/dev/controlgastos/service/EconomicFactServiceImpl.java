package orko.dev.controlgastos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

import orko.dev.controlgastos.model.BankAccount;
import orko.dev.controlgastos.model.Budget;
import orko.dev.controlgastos.model.EconomicFact;
import orko.dev.controlgastos.model.Entry;
import orko.dev.controlgastos.repository.specification.EconomicFactSpecification;
import orko.dev.controlgastos.service.security.PrincipalService;
import orko.dev.controlgastos.structs.BudgetEntryExecuted;


public class EconomicFactServiceImpl implements EconomicFactService {
	
	@Autowired
	private PrincipalService principalService;
	
	@Override
	@Transactional(readOnly=true)
	public List<EconomicFact> findLastEconomicFacts( BankAccount bankAccount) {
		Pageable pageRequest = new PageRequest(0,20,Direction.DESC,"date");
		return this.economicFactRepository.findByBankAccount(bankAccount,pageRequest);
	}

	@Override
	@Transactional(readOnly=true)
	public List<BudgetEntryExecuted> giveBudgetEntriesExecuted(Budget budget) {
		return this.economicFactRepository.giveBudgetEntriesExecuted(budget);
	}

	@Override
	@Transactional(readOnly=true)
	public List<EconomicFact> findEconomicsFact(Budget budget, Entry entry) {
		return this.economicFactRepository.findByBudgetAndEntry(budget,entry);
	}
	
	@Transactional(readOnly=true)
	public long countAllEconomicFacts() {
        return economicFactRepository.count(EconomicFactSpecification.userEquals(principalService.getPrincipalLogin()));
    }

	@Transactional(readOnly=true)
	public List<EconomicFact> findAllEconomicFacts() {
        return economicFactRepository.findAllByUser(this.principalService.getPrincipalLogin());
    }

	@Transactional(readOnly=true)
	public List<EconomicFact> findEconomicFactEntries(int firstResult, int maxResults) {
        return economicFactRepository.findAllByUser(this.principalService.getPrincipalLogin(),new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveEconomicFact(EconomicFact economicFact) {
		economicFact.setUser(principalService.getPrincipalLogin());
        economicFactRepository.save(economicFact);
    }

	public EconomicFact updateEconomicFact(EconomicFact economicFact) {
		economicFact.setUser(principalService.getPrincipalLogin());
        return economicFactRepository.save(economicFact);
    }
}
