package orko.dev.controlgastos.model;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.test.RooIntegrationTest;

import orko.dev.controlgastos.service.BudgetService;

@RooIntegrationTest(entity = Budget.class)
public class BudgetIntegrationTest extends CommonTestClass{
	
	@Autowired
    private BudgetDataOnDemand dod;
    
    @Autowired
    BudgetService budgetService;

    @Test
    public void testMarkerMethod() {
    }

	@Test
    public void testDeleteBudget() {
        Budget obj = dod.getSpecificBudget(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Budget' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Budget' failed to provide an identifier", id);
        obj = budgetService.findBudget(id);
        budgetService.deleteBudget(obj);
        budgetRepository.flush();
        Assert.assertNull("Failed to remove 'Budget' with identifier '" + id + "'", budgetService.findBudget(id));
    }
}
