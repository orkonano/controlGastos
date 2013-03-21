package orko.dev.controlgastos.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import orko.dev.controlgastos.model.BankAccount;
import orko.dev.controlgastos.model.BankTransfer;

@RooJpaRepository(domainType = BankTransfer.class)
public interface BankTransferRepository  extends CustomBasicUserRepository<BankTransfer>, BankTransferRepositoryCustom {

	List<BankTransfer> findByBankAccountDestOrBankAccountSrc( BankAccount bankAccountDesc,BankAccount bankAccountSrc, Pageable pageRequest);
}
