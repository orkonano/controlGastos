// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import orko.dev.controlgastos.model.BudgetEntry;
import orko.dev.controlgastos.service.BudgetEntryService;
import orko.dev.controlgastos.web.BudgetEntryController;

privileged aspect BudgetEntryController_Roo_Controller {
    
    @Autowired
    BudgetEntryService BudgetEntryController.budgetEntryService;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String BudgetEntryController.create(@Valid BudgetEntry budgetEntry, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, budgetEntry);
            return "budgetentrys/create";
        }
        uiModel.asMap().clear();
        budgetEntryService.saveBudgetEntry(budgetEntry);
        return "redirect:/budgetentrys/" + encodeUrlPathSegment(budgetEntry.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String BudgetEntryController.createForm(Model uiModel) {
        populateEditForm(uiModel, new BudgetEntry());
        List<String[]> dependencies = new ArrayList<String[]>();
        if (entryService.countAllEntrys() == 0) {
            dependencies.add(new String[] { "entry", "entrys" });
        }
        if (budgetService.countAllBudgets() == 0) {
            dependencies.add(new String[] { "budget", "budgets" });
        }
        uiModel.addAttribute("dependencies", dependencies);
        return "budgetentrys/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String BudgetEntryController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("budgetentry", budgetEntryService.findBudgetEntry(id));
        uiModel.addAttribute("itemId", id);
        return "budgetentrys/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String BudgetEntryController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("budgetentrys", budgetEntryService.findBudgetEntryEntries(firstResult, sizeNo));
            float nrOfPages = (float) budgetEntryService.countAllBudgetEntrys() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("budgetentrys", budgetEntryService.findAllBudgetEntrys());
        }
        return "budgetentrys/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String BudgetEntryController.update(@Valid BudgetEntry budgetEntry, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, budgetEntry);
            return "budgetentrys/update";
        }
        uiModel.asMap().clear();
        budgetEntryService.updateBudgetEntry(budgetEntry);
        return "redirect:/budgetentrys/" + encodeUrlPathSegment(budgetEntry.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String BudgetEntryController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, budgetEntryService.findBudgetEntry(id));
        return "budgetentrys/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String BudgetEntryController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        BudgetEntry budgetEntry = budgetEntryService.findBudgetEntry(id);
        budgetEntryService.deleteBudgetEntry(budgetEntry);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/budgetentrys";
    }
    
    String BudgetEntryController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}