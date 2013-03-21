package orko.dev.controlgastos.repository;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import orko.dev.controlgastos.model.BankAccount;

@RooJpaRepository(domainType = BankAccount.class)
public interface BankAccountRepository extends CustomBasicUserRepository<BankAccount>{
	
}
