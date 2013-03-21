package orko.dev.controlgastos.web;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import orko.dev.controlgastos.model.Budget;
import orko.dev.controlgastos.model.EconomicFact;
import orko.dev.controlgastos.model.Entry;
import orko.dev.controlgastos.service.BankAccountService;
import orko.dev.controlgastos.service.BudgetService;
import orko.dev.controlgastos.service.EconomicFactService;
import orko.dev.controlgastos.service.EntryService;
import orko.dev.controlgastos.service.security.PrincipalService;

@RequestMapping("/economicfacts")
@Controller
@RooWebScaffold(path = "economicfacts", formBackingObject = EconomicFact.class)
public class EconomicFactController {
	
	@Autowired
	private BudgetService budgetService;
	@Autowired
	private EntryService entryService;
	@Autowired
	private BankAccountService bankAccountService;
	@Autowired
	private EconomicFactService economicFactService;
	@Autowired
	private PrincipalService principalService;
	
	@RequestMapping(params ={"budgetId","entryId"}, produces = "text/html")
    public String listBuEntryAndBudget(@RequestParam(value = "budgetId", required = true) Long budgetId,@RequestParam(value = "entryId", required = true) Long entryId, Model uiModel) {
		Budget budget = budgetService.findBudget(budgetId);
		Entry entry = entryService.findEntry(entryId);
        uiModel.addAttribute("economicfacts", economicFactService.findEconomicsFact(budget,entry));
        addDateTimeFormatPatterns(uiModel);
        return "economicfacts/list";
    }
	
	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("economicFact_date_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
    }
	

	void populateEditForm(Model uiModel, EconomicFact economicFact) {
        uiModel.addAttribute("economicFact", economicFact);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("bankaccounts", bankAccountService.findAllBankAccounts());
        uiModel.addAttribute("budgets", budgetService.findAllBudgetsDesc());
        uiModel.addAttribute("entrys", entryService.findEntryByAttributable(true));;
        uiModel.addAttribute("principals", principalService.findAllPrincipals());
    }
}
