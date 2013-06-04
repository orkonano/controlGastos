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
import orko.dev.controlgastos.model.BankAccountIntegrationTest;
import orko.dev.controlgastos.repository.BankAccountRepository;

privileged aspect BankAccountIntegrationTest_Roo_IntegrationTest {
    
    declare @type: BankAccountIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: BankAccountIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: BankAccountIntegrationTest: @Transactional;
    
    @Autowired
    BankAccountRepository BankAccountIntegrationTest.bankAccountRepository;
    
    @Test
    public void BankAccountIntegrationTest.testCountAllBankAccounts() {
        Assert.assertNotNull("Data on demand for 'BankAccount' failed to initialize correctly", dod.getRandomBankAccount());
        long count = bankAccountService.countAllBankAccounts();
        Assert.assertTrue("Counter for 'BankAccount' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void BankAccountIntegrationTest.testFindBankAccount() {
        BankAccount obj = dod.getRandomBankAccount();
        Assert.assertNotNull("Data on demand for 'BankAccount' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'BankAccount' failed to provide an identifier", id);
        obj = bankAccountService.findBankAccount(id);
        Assert.assertNotNull("Find method for 'BankAccount' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'BankAccount' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void BankAccountIntegrationTest.testFindAllBankAccounts() {
        Assert.assertNotNull("Data on demand for 'BankAccount' failed to initialize correctly", dod.getRandomBankAccount());
        long count = bankAccountService.countAllBankAccounts();
        Assert.assertTrue("Too expensive to perform a find all test for 'BankAccount', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<BankAccount> result = bankAccountService.findAllBankAccounts();
        Assert.assertNotNull("Find all method for 'BankAccount' illegally returned null", result);
        Assert.assertTrue("Find all method for 'BankAccount' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void BankAccountIntegrationTest.testFindBankAccountEntries() {
        Assert.assertNotNull("Data on demand for 'BankAccount' failed to initialize correctly", dod.getRandomBankAccount());
        long count = bankAccountService.countAllBankAccounts();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<BankAccount> result = bankAccountService.findBankAccountEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'BankAccount' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'BankAccount' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void BankAccountIntegrationTest.testFlush() {
        BankAccount obj = dod.getRandomBankAccount();
        Assert.assertNotNull("Data on demand for 'BankAccount' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'BankAccount' failed to provide an identifier", id);
        obj = bankAccountService.findBankAccount(id);
        Assert.assertNotNull("Find method for 'BankAccount' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyBankAccount(obj);
        Integer currentVersion = obj.getVersion();
        bankAccountRepository.flush();
        Assert.assertTrue("Version for 'BankAccount' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void BankAccountIntegrationTest.testUpdateBankAccountUpdate() {
        BankAccount obj = dod.getRandomBankAccount();
        Assert.assertNotNull("Data on demand for 'BankAccount' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'BankAccount' failed to provide an identifier", id);
        obj = bankAccountService.findBankAccount(id);
        boolean modified =  dod.modifyBankAccount(obj);
        Integer currentVersion = obj.getVersion();
        BankAccount merged = bankAccountService.updateBankAccount(obj);
        bankAccountRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'BankAccount' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void BankAccountIntegrationTest.testSaveBankAccount() {
        Assert.assertNotNull("Data on demand for 'BankAccount' failed to initialize correctly", dod.getRandomBankAccount());
        BankAccount obj = dod.getNewTransientBankAccount(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'BankAccount' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'BankAccount' identifier to be null", obj.getId());
        bankAccountService.saveBankAccount(obj);
        bankAccountRepository.flush();
        Assert.assertNotNull("Expected 'BankAccount' identifier to no longer be null", obj.getId());
    }
    
}