package orko.dev.controlgastos.web;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import orko.dev.controlgastos.model.BankAccount;
import orko.dev.controlgastos.model.BankAccountType;
import orko.dev.controlgastos.model.EconomicFact;
import orko.dev.controlgastos.model.interfaces.BankOperation;
import orko.dev.controlgastos.service.EconomicFactService;

@RequestMapping("/bankaccounts")
@Controller
@RooWebScaffold(path = "bankaccounts", formBackingObject = BankAccount.class)
public class BankAccountController {
	
	
	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(HttpServletRequest request,@PathVariable("id") Long id, Model uiModel) {
		BankAccount bankAccount = bankAccountService.findBankAccount(id);
        uiModel.addAttribute("bankaccount", bankAccount);
        List<BankOperation> bankOperation = bankAccountService.findLastBankOperation(bankAccount);
        BigDecimal balance = bankAccountService.getBalance(bankAccount);
        uiModel.addAttribute("itemId", id);
        uiModel.addAttribute("balance", balance);
        uiModel.addAttribute("bankOperation", bankOperation);
        return "bankaccounts/show";
    }
	
	

	void populateEditForm(Model uiModel, BankAccount bankAccount) {
        uiModel.addAttribute("bankAccount", bankAccount);
        uiModel.addAttribute("bankaccounttypes", Arrays.asList(BankAccountType.values()));
    }
}
