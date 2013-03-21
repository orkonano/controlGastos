package orko.dev.controlgastos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

import orko.dev.controlgastos.model.BankAccount;
import orko.dev.controlgastos.model.BankTransfer;
import orko.dev.controlgastos.repository.BankTransferRepository;
import orko.dev.controlgastos.repository.specification.BankTransferSpecification;
import orko.dev.controlgastos.service.security.PrincipalService;


public class BankTransferServiceImpl implements BankTransferService {
	
	@Autowired
	private PrincipalService principalService;
	@Autowired
	private BankTransferRepository bankTransferRepository;

	@Transactional(readOnly=true)
	public long countAllBankTransfers() {
        return bankTransferRepository.count(BankTransferSpecification.userEquals(principalService.getPrincipalLogin()));
    }

	@Transactional(readOnly=true)
	public List<BankTransfer> findAllBankTransfers() {
        return bankTransferRepository.findAllByUser(principalService.getPrincipalLogin());
    }

	@Transactional(readOnly=true)
	public List<BankTransfer> findBankTransferEntries(int firstResult, int maxResults) {
        return bankTransferRepository.findAllByUser(principalService.getPrincipalLogin(),new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveBankTransfer(BankTransfer bankTransfer) {
		bankTransfer.setUser(principalService.getPrincipalLogin());
        bankTransferRepository.save(bankTransfer);
    }

	public BankTransfer updateBankTransfer(BankTransfer bankTransfer) {
		bankTransfer.setUser(principalService.getPrincipalLogin());
        return bankTransferRepository.save(bankTransfer);
    }

	@Override
	@Transactional(readOnly=true)
	public List<BankTransfer> findLastBankTransfer(BankAccount bankAccount) {
		Pageable pageRequest = new PageRequest(0,20,Direction.DESC,"date");
		return this.bankTransferRepository.findByBankAccountDestOrBankAccountSrc(bankAccount,bankAccount,pageRequest);
	}
}
