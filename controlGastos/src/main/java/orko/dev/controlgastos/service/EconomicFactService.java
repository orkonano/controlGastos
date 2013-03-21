package orko.dev.controlgastos.service;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import orko.dev.controlgastos.model.BankAccount;
import orko.dev.controlgastos.model.Budget;
import orko.dev.controlgastos.model.EconomicFact;
import orko.dev.controlgastos.model.Entry;
import orko.dev.controlgastos.structs.BudgetEntryExecuted;

@RooService(domainTypes = { orko.dev.controlgastos.model.EconomicFact.class })
public interface EconomicFactService {

	List<BudgetEntryExecuted> giveBudgetEntriesExecuted(Budget budget);

	List<EconomicFact> findEconomicsFact(Budget budget, Entry entry);

	List<EconomicFact> findLastEconomicFacts(BankAccount bankAccount);
}
