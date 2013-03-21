// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.web.security;

import java.io.UnsupportedEncodingException;
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
import orko.dev.controlgastos.model.security.Principal;
import orko.dev.controlgastos.service.security.PrincipalService;
import orko.dev.controlgastos.web.security.UserContoller;

privileged aspect UserContoller_Roo_Controller {
    
    @Autowired
    PrincipalService UserContoller.principalService;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String UserContoller.create(@Valid Principal principal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, principal);
            return "security/users/create";
        }
        uiModel.asMap().clear();
        principalService.savePrincipal(principal);
        return "redirect:/security/users/" + encodeUrlPathSegment(principal.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String UserContoller.createForm(Model uiModel) {
        populateEditForm(uiModel, new Principal());
        return "security/users/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String UserContoller.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("principal", principalService.findPrincipal(id));
        uiModel.addAttribute("itemId", id);
        return "security/users/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String UserContoller.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("principals", principalService.findPrincipalEntries(firstResult, sizeNo));
            float nrOfPages = (float) principalService.countAllPrincipals() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("principals", principalService.findAllPrincipals());
        }
        return "security/users/list";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String UserContoller.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Principal principal = principalService.findPrincipal(id);
        principalService.deletePrincipal(principal);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/security/users";
    }
    
    void UserContoller.populateEditForm(Model uiModel, Principal principal) {
        uiModel.addAttribute("principal", principal);
    }
    
    String UserContoller.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
