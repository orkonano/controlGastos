package orko.dev.controlgastos.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.transaction.annotation.Transactional;

import orko.dev.controlgastos.model.Budget;
import orko.dev.controlgastos.model.BudgetEntry;
import orko.dev.controlgastos.model.form.BudgetCopied;
import orko.dev.controlgastos.repository.BudgetEntryRepository;
import orko.dev.controlgastos.repository.specification.BudgetSpecification;
import orko.dev.controlgastos.service.security.PrincipalService;


public class BudgetServiceImpl implements BudgetService {
	
	@Autowired
	private PrincipalService principalService;
	@Autowired
	private BudgetEntryRepository budgetEntryRepository;

	@Transactional(readOnly=true)
	public long countAllBudgets() {
        return budgetRepository.count(BudgetSpecification.userEquals(principalService.getPrincipalLogin()));
    }

	@Transactional(readOnly=true)
	public List<Budget> findAllBudgets() {
        return budgetRepository.findAllByUser(principalService.getPrincipalLogin());
    }

	@Transactional(readOnly=true)
	public List<Budget> findBudgetEntries(int firstResult, int maxResults) {
        return budgetRepository.findAllByUser(principalService.getPrincipalLogin(),new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveBudget(Budget budget) {
		budget.setUser(principalService.getPrincipalLogin());
        budgetRepository.save(budget);
    }

	public Budget updateBudget(Budget budget) {
		budget.setUser(principalService.getPrincipalLogin());
        return budgetRepository.save(budget);
    }

	@Override
	@Transactional(readOnly=true)
	public List<Budget> findBudgetEntriesDesc(int firstResult, int maxResults) {
		return budgetRepository.findAllByUser(principalService.getPrincipalLogin(),new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults,Direction.DESC,"id")).getContent();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Budget> findAllBudgetsDesc() {
		Sort sort = new Sort(new Order(Direction.DESC,"id"));
		return budgetRepository.findAllByUser(principalService.getPrincipalLogin(),sort);
	}

	@Override
	@Transactional
	public void saveFromOldBudget(BudgetCopied budgetCopied) {
		Budget newBudget = budgetCopied.getNewBudget();
		newBudget.setUser(budgetCopied.getOldBudget().getUser());
		newBudget = this.budgetRepository.save(newBudget);
		Budget oldBudget =  this.budgetRepository.findOne(budgetCopied.getOldBudget().getId());
		BudgetEntry newBudgetEntry = null;
		for (BudgetEntry budgetEntry : oldBudget.getBudgetEntries()) {
			try {
				newBudgetEntry = (BudgetEntry)budgetEntry.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			newBudgetEntry.setBudget(newBudget);
			this.budgetEntryRepository.save(newBudgetEntry);
		}
	}

	@Override
	public Budget findBudgetByDescription(Budget budgetToEvaluate) {
		if (budgetToEvaluate.getId() == null){
			return this.budgetRepository.findBudgetByDescriptionAndUser(budgetToEvaluate.getDescription(),principalService.getPrincipalLogin());
		}else{
			return this.budgetRepository.findBudgetByDescriptionAndUserAndIdNot(budgetToEvaluate.getDescription(),principalService.getPrincipalLogin(),budgetToEvaluate.getId());
		}
	}
}
