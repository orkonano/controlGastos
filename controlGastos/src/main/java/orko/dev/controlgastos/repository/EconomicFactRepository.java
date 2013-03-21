package orko.dev.controlgastos.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import orko.dev.controlgastos.model.BankAccount;
import orko.dev.controlgastos.model.Budget;
import orko.dev.controlgastos.model.EconomicFact;
import orko.dev.controlgastos.model.Entry;
import orko.dev.controlgastos.structs.BudgetEntryExecuted;

@RooJpaRepository(domainType = EconomicFact.class)
public interface EconomicFactRepository extends PagingAndSortingRepository<EconomicFact,Long>,EconomicFactRepositoryCustom, CustomBasicUserRepository<EconomicFact> {
	
	@Query(" SELECT new orko.dev.controlgastos.structs.BudgetEntryExecuted(ef.entry,ef.budget,SUM(ef.amount)) FROM EconomicFact as ef WHERE ef.budget = ?1 GROUP BY ef.entry ")
	public List<BudgetEntryExecuted> giveBudgetEntriesExecuted(Budget budget);
	
	public List<EconomicFact> findByBudgetAndEntry(Budget budget, Entry entry);
	
	public List<EconomicFact> findByBankAccount(BankAccount bankAccount, Pageable pageRequest);
	
}
