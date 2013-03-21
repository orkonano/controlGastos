package orko.dev.controlgastos.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import orko.dev.controlgastos.model.Budget;
import orko.dev.controlgastos.model.BudgetEntry;
import orko.dev.controlgastos.model.Entry;
import orko.dev.controlgastos.model.form.BudgetCopied;
import orko.dev.controlgastos.service.BudgetEntryService;
import orko.dev.controlgastos.service.BudgetService;
import orko.dev.controlgastos.service.EconomicFactService;
import orko.dev.controlgastos.service.EntryService;
import orko.dev.controlgastos.structs.BudgetEntryExecuted;
import orko.dev.controlgastos.util.comparator.EntryComparatorImpl;
import orko.dev.controlgastos.util.map.ICollectionToMapTransform;
import orko.dev.controlgastos.util.map.MapUtils;
import orko.dev.controlgastos.web.validator.BudgetValidator;

@RequestMapping("/budgets")
@Controller
@RooWebScaffold(path = "budgets", formBackingObject = Budget.class)
public class BudgetController {

	@Autowired
	private EntryComparatorImpl entryComparator;
	@Autowired
	private EntryService entryService;
	@Autowired
	private MapUtils mapUtils;
	@Autowired
	private EconomicFactService economicFactService;
	@Autowired
	BudgetService budgetService;
	@Autowired
	BudgetEntryService budgetEntryService;
	@Autowired
	private BudgetValidator budgetValidator;

	@RequestMapping(value = "/{id}", produces = "text/html")
	public String show(@PathVariable("id") Long id, Model uiModel) {
		Budget budget = budgetService.findBudget(id);
		List<BudgetEntry> budgetEntries = new ArrayList<BudgetEntry>(
				budget.getBudgetEntries());
		List<Entry> allEntries = this.entryService.findAllEntrys();
		Collections.sort(allEntries, entryComparator);
		Map<Long, BudgetEntry> mapBudgetEntries = this.mapUtils.createMap(
				budgetEntries,
				new ICollectionToMapTransform<Long, BudgetEntry>() {

					@Override
					public void insertIntoMap(Map<Long, BudgetEntry> map,
							BudgetEntry object) {
						map.put(object.getEntry().getId(), object);
					}

				});
		List<BudgetEntryExecuted> budgetEntriesExecuted = economicFactService
				.giveBudgetEntriesExecuted(budget);
		Map<Long, BudgetEntryExecuted> mapBudgetEntriesExecuted = this.mapUtils
				.createMap(
						budgetEntriesExecuted,
						new ICollectionToMapTransform<Long, BudgetEntryExecuted>() {

							@Override
							public void insertIntoMap(
									Map<Long, BudgetEntryExecuted> map,
									BudgetEntryExecuted object) {
								map.put(object.getEntry().getId(), object);
							}

						});
		uiModel.addAttribute("budget", budget);
		uiModel.addAttribute("itemId", id);
		uiModel.addAttribute("budgetEntries", mapBudgetEntries);
		uiModel.addAttribute("budgetEntriesExecuted", mapBudgetEntriesExecuted);
		uiModel.addAttribute("allEntries", allEntries);
		return "budgets/show";
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new Budget());
		return "budgets/create";
	}

	void populateEditForm(Model uiModel, Budget budget) {
		uiModel.addAttribute("budget", budget);
		uiModel.addAttribute("budgetentrys",
				budgetEntryService.findAllBudgetEntrys());
	}

	@RequestMapping(produces = "text/html")
	public String list(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1)
					* sizeNo;
			uiModel.addAttribute("budgets",
					budgetService.findBudgetEntriesDesc(firstResult, sizeNo));
			float nrOfPages = (float) budgetService.countAllBudgets() / sizeNo;
			uiModel.addAttribute(
					"maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			uiModel.addAttribute("budgets", budgetService.findAllBudgetsDesc());
		}
		return "budgets/list";
	}

	@RequestMapping(value = "/budgetCopy", produces = "text/html")
	public String budgetCopyForm(HttpServletRequest request, Model uiModel) {
		copyBudgetEditForm(uiModel);
		return "budgets/copyBudget";
	}

	private void copyBudgetEditForm(Model uiModel) {
		BudgetCopied budgetCopied = new BudgetCopied();
		uiModel.addAttribute("budgetCopied", budgetCopied);
		uiModel.addAttribute("budgets", budgetService.findAllBudgetsDesc());
	}

	@RequestMapping(value = "/budgetCopy",produces = "text/html", method = RequestMethod.POST)
	public String budgetCopy(@Valid BudgetCopied budgetCopied,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest) {
		budgetValidator.validate(budgetCopied.getNewBudget(), bindingResult);
		if (bindingResult.hasErrors()) {
			copyBudgetEditForm(uiModel);
			uiModel.addAllAttributes(bindingResult.getModel());
			return "budgets/copyBudget";
		}
		uiModel.asMap().clear();
		this.budgetService.saveFromOldBudget(budgetCopied);
		return "redirect:/budgets/"
				+ encodeUrlPathSegment(budgetCopied.getNewBudget().getId().toString(),
						httpServletRequest);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Budget budget, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		this.budgetValidator.validate(budget,bindingResult);
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, budget);
            return "budgets/create";
        }
        uiModel.asMap().clear();
        budgetService.saveBudget(budget);
        return "redirect:/budgets/" + encodeUrlPathSegment(budget.getId().toString(), httpServletRequest);
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Budget budget, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		this.budgetValidator.validate(budget,bindingResult);
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, budget);
            return "budgets/update";
        }
        uiModel.asMap().clear();
        budgetService.updateBudget(budget);
        return "redirect:/budgets/" + encodeUrlPathSegment(budget.getId().toString(), httpServletRequest);
    }
}
