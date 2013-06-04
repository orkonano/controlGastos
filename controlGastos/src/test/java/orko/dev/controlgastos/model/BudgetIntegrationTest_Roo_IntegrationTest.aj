// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.model;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import orko.dev.controlgastos.model.BudgetIntegrationTest;
import orko.dev.controlgastos.repository.BudgetRepository;

privileged aspect BudgetIntegrationTest_Roo_IntegrationTest {
    
    declare @type: BudgetIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: BudgetIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: BudgetIntegrationTest: @Transactional;
    
    @Autowired
    BudgetRepository BudgetIntegrationTest.budgetRepository;
    
    @Test
    public void BudgetIntegrationTest.testCountAllBudgets() {
        Assert.assertNotNull("Data on demand for 'Budget' failed to initialize correctly", dod.getRandomBudget());
        long count = budgetService.countAllBudgets();
        Assert.assertTrue("Counter for 'Budget' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void BudgetIntegrationTest.testFindBudget() {
        Budget obj = dod.getRandomBudget();
        Assert.assertNotNull("Data on demand for 'Budget' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Budget' failed to provide an identifier", id);
        obj = budgetService.findBudget(id);
        Assert.assertNotNull("Find method for 'Budget' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Budget' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void BudgetIntegrationTest.testFindAllBudgets() {
        Assert.assertNotNull("Data on demand for 'Budget' failed to initialize correctly", dod.getRandomBudget());
        long count = budgetService.countAllBudgets();
        Assert.assertTrue("Too expensive to perform a find all test for 'Budget', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Budget> result = budgetService.findAllBudgets();
        Assert.assertNotNull("Find all method for 'Budget' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Budget' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void BudgetIntegrationTest.testFindBudgetEntries() {
        Assert.assertNotNull("Data on demand for 'Budget' failed to initialize correctly", dod.getRandomBudget());
        long count = budgetService.countAllBudgets();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Budget> result = budgetService.findBudgetEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Budget' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Budget' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void BudgetIntegrationTest.testFlush() {
        Budget obj = dod.getRandomBudget();
        Assert.assertNotNull("Data on demand for 'Budget' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Budget' failed to provide an identifier", id);
        obj = budgetService.findBudget(id);
        Assert.assertNotNull("Find method for 'Budget' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyBudget(obj);
        Integer currentVersion = obj.getVersion();
        budgetRepository.flush();
        Assert.assertTrue("Version for 'Budget' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void BudgetIntegrationTest.testUpdateBudgetUpdate() {
        Budget obj = dod.getRandomBudget();
        Assert.assertNotNull("Data on demand for 'Budget' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Budget' failed to provide an identifier", id);
        obj = budgetService.findBudget(id);
        boolean modified =  dod.modifyBudget(obj);
        Integer currentVersion = obj.getVersion();
        Budget merged = budgetService.updateBudget(obj);
        budgetRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Budget' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void BudgetIntegrationTest.testSaveBudget() {
        Assert.assertNotNull("Data on demand for 'Budget' failed to initialize correctly", dod.getRandomBudget());
        Budget obj = dod.getNewTransientBudget(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Budget' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Budget' identifier to be null", obj.getId());
        budgetService.saveBudget(obj);
        budgetRepository.flush();
        Assert.assertNotNull("Expected 'Budget' identifier to no longer be null", obj.getId());
    }
    
}