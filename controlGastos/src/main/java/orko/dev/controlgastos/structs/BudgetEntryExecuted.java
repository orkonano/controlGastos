package orko.dev.controlgastos.structs;

import java.math.BigDecimal;

import javax.persistence.Id;

import orko.dev.controlgastos.model.Budget;
import orko.dev.controlgastos.model.Entry;


public class BudgetEntryExecuted {
	@Id
	private Entry entry;
	private Budget budget;
	private BigDecimal amount;
	
	
	public BudgetEntryExecuted(Entry entry, Budget budget, BigDecimal amount) {
		super();
		this.entry = entry;
		this.budget = budget;
		this.amount = amount;
	}
	public Entry getEntry() {
		return entry;
	}
	public void setEntry(Entry entry) {
		this.entry = entry;
	}
	public Budget getBudget() {
		return budget;
	}
	public void setBudget(Budget budget) {
		this.budget = budget;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	

}
