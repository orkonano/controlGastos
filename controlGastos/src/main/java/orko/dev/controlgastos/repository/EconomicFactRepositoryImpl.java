package orko.dev.controlgastos.repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import orko.dev.controlgastos.model.BankAccount;
import orko.dev.controlgastos.model.EntryType;

public class EconomicFactRepositoryImpl implements EconomicFactRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public BigDecimal getBalanceByBankAccount(BankAccount bankAccount) {
		BigDecimal positiveBalance = this.getBalanceByBanlAccount(bankAccount, Arrays.asList(EntryType.INGRESS,EntryType.OPEN_BALANCE));
		BigDecimal negativeBalance = this.getBalanceByBanlAccount(bankAccount, Arrays.asList(EntryType.SAVING,EntryType.LOSS));;
		return positiveBalance.subtract(negativeBalance);
	}
	
	private BigDecimal getBalanceByBanlAccount(BankAccount bankAccount, List<EntryType> entriesType){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT COALESCE(SUM(ef.amount),0) FROM EconomicFact as ef ");
		jpql.append(" WHERE ef.bankAccount = :bank AND ef.entry.entryType IN (:entryType) ");
		Query query = this.em.createQuery(jpql.toString());
		query.setParameter("bank", bankAccount);
		query.setParameter("entryType", entriesType);
		try{
			return (BigDecimal) query.getSingleResult();
		}catch(NoResultException ex){
			return BigDecimal.ZERO;
		}
	}

}
