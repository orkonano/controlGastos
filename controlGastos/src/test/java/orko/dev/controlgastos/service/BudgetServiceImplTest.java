package orko.dev.controlgastos.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import orko.dev.controlgastos.model.Budget;
import orko.dev.controlgastos.model.CommonTestClass;
import orko.dev.controlgastos.model.form.BudgetCopied;
import orko.dev.controlgastos.repository.BudgetRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
public class BudgetServiceImplTest extends CommonTestClass{

	@Autowired
    private BudgetService budgetServiceImpl;
	@Autowired
	private BudgetRepository budgetRepository;

   
    @Test
    @Transactional
    public void saveFromOldBudget() {
    	List<Budget> budgets =this.budgetServiceImpl.findAllBudgets(); 
    	Budget oldBudget = budgets.get(0);
    	Budget newBudget = new Budget();
    	newBudget.setDescription("new Budget 2");
    	BudgetCopied budgetCopied = new BudgetCopied();
    	budgetCopied.setNewBudget(newBudget);
    	budgetCopied.setOldBudget(oldBudget);
    	budgetServiceImpl.saveFromOldBudget(budgetCopied);
    	newBudget = budgetCopied.getNewBudget();
    	budgetRepository.flush();
    	Assert.assertNotNull("Expected 'BankAccount' identifier to no longer be null", newBudget.getId());
    	Assert.assertNotSame(newBudget,oldBudget);
    }

   
}
