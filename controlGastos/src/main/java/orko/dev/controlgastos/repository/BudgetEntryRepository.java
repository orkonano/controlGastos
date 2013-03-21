package orko.dev.controlgastos.repository;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import orko.dev.controlgastos.model.BudgetEntry;

@RooJpaRepository(domainType = BudgetEntry.class)
public interface BudgetEntryRepository extends CustomBasicUserRepository<BudgetEntry>{
}
