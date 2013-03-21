package orko.dev.controlgastos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import orko.dev.controlgastos.model.BudgetEntry;
import orko.dev.controlgastos.service.BudgetService;
import orko.dev.controlgastos.service.EntryService;

@RequestMapping("/budgetentrys")
@Controller
@RooWebScaffold(path = "budgetentrys", formBackingObject = BudgetEntry.class)
public class BudgetEntryController {

	@Autowired
	private BudgetService budgetService;
	@Autowired
	private EntryService entryService;

	void populateEditForm(Model uiModel, BudgetEntry budgetEntry) {
		uiModel.addAttribute("budgetEntry", budgetEntry);
		uiModel.addAttribute("budgets", budgetService.findAllBudgetsDesc());
		uiModel.addAttribute("entrys", entryService.findEntryByAttributable(true));
	}

}
