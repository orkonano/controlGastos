// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.service;

import java.util.List;
import orko.dev.controlgastos.model.Budget;
import orko.dev.controlgastos.service.BudgetService;

privileged aspect BudgetService_Roo_Service {
    
    public abstract long BudgetService.countAllBudgets();    
    public abstract void BudgetService.deleteBudget(Budget budget);    
    public abstract Budget BudgetService.findBudget(Long id);    
    public abstract List<Budget> BudgetService.findAllBudgets();    
    public abstract List<Budget> BudgetService.findBudgetEntries(int firstResult, int maxResults);    
    public abstract void BudgetService.saveBudget(Budget budget);    
    public abstract Budget BudgetService.updateBudget(Budget budget);    
}
