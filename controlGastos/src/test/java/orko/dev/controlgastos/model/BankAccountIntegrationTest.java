package orko.dev.controlgastos.model;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.test.RooIntegrationTest;

import orko.dev.controlgastos.service.BankAccountService;

@RooIntegrationTest(entity = BankAccount.class)
public class BankAccountIntegrationTest extends CommonTestClass{
	
	@Autowired
    private BankAccountDataOnDemand dod;
    
    @Autowired
    BankAccountService bankAccountService;

    @Test
    public void testMarkerMethod() {
    }

	@Test
    public void testDeleteBankAccount() {
        BankAccount obj = dod.getSpecificBankAccount(2);
        Assert.assertNotNull("Data on demand for 'BankAccount' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'BankAccount' failed to provide an identifier", id);
        obj = bankAccountService.findBankAccount(id);
        bankAccountService.deleteBankAccount(obj);
        bankAccountRepository.flush();
        Assert.assertNull("Failed to remove 'BankAccount' with identifier '" + id + "'", bankAccountService.findBankAccount(id));
    }
}
