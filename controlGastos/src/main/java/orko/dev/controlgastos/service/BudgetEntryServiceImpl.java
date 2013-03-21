package orko.dev.controlgastos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import orko.dev.controlgastos.model.Budget;
import orko.dev.controlgastos.model.BudgetEntry;
import orko.dev.controlgastos.repository.specification.BudgetEntrySpecification;
import orko.dev.controlgastos.service.security.PrincipalService;


public class BudgetEntryServiceImpl implements BudgetEntryService {
	
	@Autowired
	private PrincipalService principalService;

	@Transactional(readOnly=true)
	public long countAllBudgetEntrys() {
        return budgetEntryRepository.count(BudgetEntrySpecification.userEquals(principalService.getPrincipalLogin()));
    }

	@Transactional(readOnly=true)
	public List<BudgetEntry> findAllBudgetEntrys() {
        return budgetEntryRepository.findAllByUser(principalService.getPrincipalLogin());
    }


	@Transactional(readOnly=true)
	public List<BudgetEntry> findBudgetEntryEntries(int firstResult, int maxResults) {
        return budgetEntryRepository.findAllByUser(principalService.getPrincipalLogin(),new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveBudgetEntry(BudgetEntry budgetEntry) {
		budgetEntry.setUser(principalService.getPrincipalLogin());
        budgetEntryRepository.save(budgetEntry);
    }

	public BudgetEntry updateBudgetEntry(BudgetEntry budgetEntry) {
		budgetEntry.setUser(principalService.getPrincipalLogin());
        return budgetEntryRepository.save(budgetEntry);
    }
}
