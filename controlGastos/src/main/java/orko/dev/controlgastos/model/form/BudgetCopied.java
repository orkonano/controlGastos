package orko.dev.controlgastos.model.form;

import javax.validation.Valid;

import orko.dev.controlgastos.model.Budget;

public class BudgetCopied {
	
	@Valid
	private Budget newBudget;
	private Budget oldBudget;
	
	public BudgetCopied(){
		newBudget = new Budget();
	}
	
	public Budget getNewBudget() {
		return newBudget;
	}
	public void setNewBudget(Budget newBudget) {
		this.newBudget = newBudget;
	}
	public Budget getOldBudget() {
		return oldBudget;
	}
	public void setOldBudget(Budget oldBudget) {
		this.oldBudget = oldBudget;
	}
	
	public String getDescription(){
		return this.newBudget.getDescription();
	}

}
