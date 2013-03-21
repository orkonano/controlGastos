package orko.dev.controlgastos.model;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import orko.dev.controlgastos.model.security.Principal;

public class BudgetEntryTest {

    private BudgetEntry budgetEntry = new BudgetEntry();
    
    
    @Test
    public void cloneTest() throws CloneNotSupportedException{
    	budgetEntry.setId(Long.valueOf(1));
    	budgetEntry.setAmount(BigDecimal.ONE);
    	Budget budget = new Budget();
    	budget.setId(Long.valueOf(1));
    	budgetEntry.setBudget(budget);
    	Entry entry = new Entry();
    	entry.setId(Long.valueOf(1));
    	budgetEntry.setEntry(entry);
    	Principal user = new Principal();
    	user.setId(Long.valueOf(1));
    	budgetEntry.setUser(user);
    	BudgetEntry newBudgetEntry = (BudgetEntry) budgetEntry.clone();
    	Assert.assertNull(newBudgetEntry.getId());
    	Assert.assertEquals(newBudgetEntry.getAmount(), budgetEntry.getAmount());
    	Assert.assertEquals(newBudgetEntry.getBudget(), budgetEntry.getBudget());
    	Assert.assertEquals(newBudgetEntry.getEntry(), budgetEntry.getEntry());
    	Assert.assertEquals(newBudgetEntry.getUser(), budgetEntry.getUser());
    }
}
