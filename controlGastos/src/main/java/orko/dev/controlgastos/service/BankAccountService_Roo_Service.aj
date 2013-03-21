// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.service;

import java.util.List;
import orko.dev.controlgastos.model.BankAccount;
import orko.dev.controlgastos.service.BankAccountService;

privileged aspect BankAccountService_Roo_Service {
    
    public abstract long BankAccountService.countAllBankAccounts();    
    public abstract void BankAccountService.deleteBankAccount(BankAccount bankAccount);    
    public abstract BankAccount BankAccountService.findBankAccount(Long id);    
    public abstract List<BankAccount> BankAccountService.findAllBankAccounts();    
    public abstract List<BankAccount> BankAccountService.findBankAccountEntries(int firstResult, int maxResults);    
    public abstract void BankAccountService.saveBankAccount(BankAccount bankAccount);    
    public abstract BankAccount BankAccountService.updateBankAccount(BankAccount bankAccount);    
}
