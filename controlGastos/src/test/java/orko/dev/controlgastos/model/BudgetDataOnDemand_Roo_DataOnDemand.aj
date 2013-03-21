// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import orko.dev.controlgastos.model.Budget;
import orko.dev.controlgastos.model.BudgetDataOnDemand;
import orko.dev.controlgastos.model.security.Principal;
import orko.dev.controlgastos.repository.BudgetRepository;
import orko.dev.controlgastos.service.BudgetService;

privileged aspect BudgetDataOnDemand_Roo_DataOnDemand {
    
    declare @type: BudgetDataOnDemand: @Component;
    
    private Random BudgetDataOnDemand.rnd = new SecureRandom();
    
    private List<Budget> BudgetDataOnDemand.data;
    
    @Autowired
    BudgetService BudgetDataOnDemand.budgetService;
    
    @Autowired
    BudgetRepository BudgetDataOnDemand.budgetRepository;
    
    public Budget BudgetDataOnDemand.getNewTransientBudget(int index) {
        Budget obj = new Budget();
        setDescription(obj, index);
        setUser(obj, index);
        return obj;
    }
    
    public void BudgetDataOnDemand.setDescription(Budget obj, int index) {
        String description = "description_" + index;
        if (description.length() > 200) {
            description = new Random().nextInt(10) + description.substring(1, 200);
        }
        obj.setDescription(description);
    }
    
    public void BudgetDataOnDemand.setUser(Budget obj, int index) {
        Principal user = null;
        obj.setUser(user);
    }
    
    public Budget BudgetDataOnDemand.getSpecificBudget(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Budget obj = data.get(index);
        Long id = obj.getId();
        return budgetService.findBudget(id);
    }
    
    public Budget BudgetDataOnDemand.getRandomBudget() {
        init();
        Budget obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return budgetService.findBudget(id);
    }
    
    public boolean BudgetDataOnDemand.modifyBudget(Budget obj) {
        return false;
    }
    
    public void BudgetDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = budgetService.findBudgetEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Budget' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Budget>();
        for (int i = 0; i < 10; i++) {
            Budget obj = getNewTransientBudget(i);
            try {
                budgetService.saveBudget(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            budgetRepository.flush();
            data.add(obj);
        }
    }
    
}
