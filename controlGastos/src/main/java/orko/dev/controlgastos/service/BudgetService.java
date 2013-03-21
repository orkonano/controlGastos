package orko.dev.controlgastos.service;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import orko.dev.controlgastos.model.Budget;
import orko.dev.controlgastos.model.form.BudgetCopied;

@RooService(domainTypes = { orko.dev.controlgastos.model.Budget.class })
public interface BudgetService {

	List<Budget> findBudgetEntriesDesc(int firstResult, int sizeNo);

	List<Budget> findAllBudgetsDesc();
	
	public void saveFromOldBudget(BudgetCopied badgetCopied);

	Budget findBudgetByDescription(Budget budgetToEvaluate);
}
