// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.model;

import java.math.BigDecimal;
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
import orko.dev.controlgastos.model.BudgetEntry;
import orko.dev.controlgastos.model.BudgetEntryDataOnDemand;
import orko.dev.controlgastos.model.Entry;
import orko.dev.controlgastos.model.EntryDataOnDemand;
import orko.dev.controlgastos.model.security.Principal;
import orko.dev.controlgastos.repository.BudgetEntryRepository;
import orko.dev.controlgastos.service.BudgetEntryService;

privileged aspect BudgetEntryDataOnDemand_Roo_DataOnDemand {
    
    declare @type: BudgetEntryDataOnDemand: @Component;
    
    private Random BudgetEntryDataOnDemand.rnd = new SecureRandom();
    
    private List<BudgetEntry> BudgetEntryDataOnDemand.data;
    
    @Autowired
    private BudgetDataOnDemand BudgetEntryDataOnDemand.budgetDataOnDemand;
    
    @Autowired
    private EntryDataOnDemand BudgetEntryDataOnDemand.entryDataOnDemand;
    
    @Autowired
    BudgetEntryService BudgetEntryDataOnDemand.budgetEntryService;
    
    @Autowired
    BudgetEntryRepository BudgetEntryDataOnDemand.budgetEntryRepository;
    
    public BudgetEntry BudgetEntryDataOnDemand.getNewTransientBudgetEntry(int index) {
        BudgetEntry obj = new BudgetEntry();
        setAmount(obj, index);
        setBudget(obj, index);
        setEntry(obj, index);
        setUser(obj, index);
        return obj;
    }
    
    public void BudgetEntryDataOnDemand.setAmount(BudgetEntry obj, int index) {
        BigDecimal amount = BigDecimal.valueOf(index);
        if (amount.compareTo(new BigDecimal("99999.99")) == 1) {
            amount = new BigDecimal("99999.99");
        }
        obj.setAmount(amount);
    }
    
    public void BudgetEntryDataOnDemand.setBudget(BudgetEntry obj, int index) {
        Budget budget = budgetDataOnDemand.getRandomBudget();
        obj.setBudget(budget);
    }
    
    public void BudgetEntryDataOnDemand.setEntry(BudgetEntry obj, int index) {
        Entry entry = entryDataOnDemand.getRandomEntry();
        obj.setEntry(entry);
    }
    
    public void BudgetEntryDataOnDemand.setUser(BudgetEntry obj, int index) {
        Principal user = null;
        obj.setUser(user);
    }
    
    public BudgetEntry BudgetEntryDataOnDemand.getSpecificBudgetEntry(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        BudgetEntry obj = data.get(index);
        Long id = obj.getId();
        return budgetEntryService.findBudgetEntry(id);
    }
    
    public BudgetEntry BudgetEntryDataOnDemand.getRandomBudgetEntry() {
        init();
        BudgetEntry obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return budgetEntryService.findBudgetEntry(id);
    }
    
    public boolean BudgetEntryDataOnDemand.modifyBudgetEntry(BudgetEntry obj) {
        return false;
    }
    
    public void BudgetEntryDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = budgetEntryService.findBudgetEntryEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'BudgetEntry' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<BudgetEntry>();
        for (int i = 0; i < 10; i++) {
            BudgetEntry obj = getNewTransientBudgetEntry(i);
            try {
                budgetEntryService.saveBudgetEntry(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            budgetEntryRepository.flush();
            data.add(obj);
        }
    }
    
}