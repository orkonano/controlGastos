package orko.dev.controlgastos.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import orko.dev.controlgastos.model.Budget;
import orko.dev.controlgastos.service.BudgetService;

@Component
public class BudgetValidator implements Validator {
	
	@Autowired
	private BudgetService budgetService;
	

	@Override
	public boolean supports(Class<?> clazz) {
		return Budget.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Budget budgetToEvaluate = (Budget)target;
		Budget budgetFounded = this.budgetService.findBudgetByDescription(budgetToEvaluate);
		if (budgetFounded != null){
			errors.rejectValue("description", "budget_description.duplicate");
		}

	}

}
