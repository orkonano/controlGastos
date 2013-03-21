package orko.dev.controlgastos.repository;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import orko.dev.controlgastos.model.BankAccount;
import orko.dev.controlgastos.model.BankTransfer;

public class BankTransferRepositoryImpl implements BankTransferRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public BigDecimal getBalanceByBankAccount(BankAccount bankAccount) {
		BigDecimal positiveBalance = this.getBalanceByBankAccount(bankAccount, true);
		BigDecimal negativeBalance = this.getBalanceByBankAccount(bankAccount, false);;
		return positiveBalance.subtract(negativeBalance);
	}
	
	private BigDecimal getBalanceByBankAccount(BankAccount bankAccount, Boolean ingress){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT COALESCE(SUM(bt.amount),0) FROM "+BankTransfer.class.getName()+" as bt ");
		jpql.append(" WHERE ").append(ingress?"bt.bankAccountDest ":"bt.bankAccountSrc ").append(" = :bank");
		Query query = this.em.createQuery(jpql.toString());
		query.setParameter("bank", bankAccount);
		try{
			return (BigDecimal) query.getSingleResult();
		}catch(NoResultException ex){
			return BigDecimal.ZERO;
		}
	}

}
