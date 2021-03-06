// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import orko.dev.controlgastos.model.EconomicFact;
import orko.dev.controlgastos.web.EconomicFactController;

privileged aspect EconomicFactController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String EconomicFactController.create(@Valid EconomicFact economicFact, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, economicFact);
            return "economicfacts/create";
        }
        uiModel.asMap().clear();
        economicFactService.saveEconomicFact(economicFact);
        return "redirect:/economicfacts/" + encodeUrlPathSegment(economicFact.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String EconomicFactController.createForm(Model uiModel) {
        populateEditForm(uiModel, new EconomicFact());
        List<String[]> dependencies = new ArrayList<String[]>();
        if (entryService.countAllEntrys() == 0) {
            dependencies.add(new String[] { "entry", "entrys" });
        }
        if (budgetService.countAllBudgets() == 0) {
            dependencies.add(new String[] { "budget", "budgets" });
        }
        if (bankAccountService.countAllBankAccounts() == 0) {
            dependencies.add(new String[] { "bankaccount", "bankaccounts" });
        }
        uiModel.addAttribute("dependencies", dependencies);
        return "economicfacts/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String EconomicFactController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("economicfact", economicFactService.findEconomicFact(id));
        uiModel.addAttribute("itemId", id);
        return "economicfacts/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String EconomicFactController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("economicfacts", economicFactService.findEconomicFactEntries(firstResult, sizeNo));
            float nrOfPages = (float) economicFactService.countAllEconomicFacts() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("economicfacts", economicFactService.findAllEconomicFacts());
        }
        addDateTimeFormatPatterns(uiModel);
        return "economicfacts/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String EconomicFactController.update(@Valid EconomicFact economicFact, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, economicFact);
            return "economicfacts/update";
        }
        uiModel.asMap().clear();
        economicFactService.updateEconomicFact(economicFact);
        return "redirect:/economicfacts/" + encodeUrlPathSegment(economicFact.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String EconomicFactController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, economicFactService.findEconomicFact(id));
        return "economicfacts/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String EconomicFactController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        EconomicFact economicFact = economicFactService.findEconomicFact(id);
        economicFactService.deleteEconomicFact(economicFact);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/economicfacts";
    }
    
    String EconomicFactController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
