// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.model;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import orko.dev.controlgastos.model.BankAccount;
import orko.dev.controlgastos.model.BankAccountDataOnDemand;
import orko.dev.controlgastos.model.BankTransfer;
import orko.dev.controlgastos.model.BankTransferDataOnDemand;
import orko.dev.controlgastos.model.security.Principal;
import orko.dev.controlgastos.repository.BankTransferRepository;
import orko.dev.controlgastos.service.BankTransferService;

privileged aspect BankTransferDataOnDemand_Roo_DataOnDemand {
    
    declare @type: BankTransferDataOnDemand: @Component;
    
    private Random BankTransferDataOnDemand.rnd = new SecureRandom();
    
    private List<BankTransfer> BankTransferDataOnDemand.data;
    
    @Autowired
    private BankAccountDataOnDemand BankTransferDataOnDemand.bankAccountDataOnDemand;
    
    @Autowired
    BankTransferService BankTransferDataOnDemand.bankTransferService;
    
    @Autowired
    BankTransferRepository BankTransferDataOnDemand.bankTransferRepository;
    
    public BankTransfer BankTransferDataOnDemand.getNewTransientBankTransfer(int index) {
        BankTransfer obj = new BankTransfer();
        setAmount(obj, index);
        setBankAccountDest(obj, index);
        setBankAccountSrc(obj, index);
        setDate(obj, index);
        setDescription(obj, index);
        setUser(obj, index);
        return obj;
    }
    
    public void BankTransferDataOnDemand.setAmount(BankTransfer obj, int index) {
        BigDecimal amount = BigDecimal.valueOf(index);
        if (amount.compareTo(new BigDecimal("99999.99")) == 1) {
            amount = new BigDecimal("99999.99");
        }
        obj.setAmount(amount);
    }
    
    public void BankTransferDataOnDemand.setBankAccountDest(BankTransfer obj, int index) {
        BankAccount bankAccountDest = bankAccountDataOnDemand.getRandomBankAccount();
        obj.setBankAccountDest(bankAccountDest);
    }
    
    public void BankTransferDataOnDemand.setBankAccountSrc(BankTransfer obj, int index) {
        BankAccount bankAccountSrc = bankAccountDataOnDemand.getRandomBankAccount();
        obj.setBankAccountSrc(bankAccountSrc);
    }
    
    public void BankTransferDataOnDemand.setDate(BankTransfer obj, int index) {
        Date date = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setDate(date);
    }
    
    public void BankTransferDataOnDemand.setDescription(BankTransfer obj, int index) {
        String description = "description_" + index;
        obj.setDescription(description);
    }
    
    public void BankTransferDataOnDemand.setUser(BankTransfer obj, int index) {
        Principal user = null;
        obj.setUser(user);
    }
    
    public BankTransfer BankTransferDataOnDemand.getSpecificBankTransfer(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        BankTransfer obj = data.get(index);
        Long id = obj.getId();
        return bankTransferService.findBankTransfer(id);
    }
    
    public BankTransfer BankTransferDataOnDemand.getRandomBankTransfer() {
        init();
        BankTransfer obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return bankTransferService.findBankTransfer(id);
    }
    
    public boolean BankTransferDataOnDemand.modifyBankTransfer(BankTransfer obj) {
        return false;
    }
    
    public void BankTransferDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = bankTransferService.findBankTransferEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'BankTransfer' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<BankTransfer>();
        for (int i = 0; i < 10; i++) {
            BankTransfer obj = getNewTransientBankTransfer(i);
            try {
                bankTransferService.saveBankTransfer(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            bankTransferRepository.flush();
            data.add(obj);
        }
    }
    
}
