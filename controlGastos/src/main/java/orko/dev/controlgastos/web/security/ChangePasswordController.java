package orko.dev.controlgastos.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import orko.dev.controlgastos.model.security.Principal;
import orko.dev.controlgastos.model.security.form.PrincipalChangePassword;
import orko.dev.controlgastos.service.security.PrincipalService;
import orko.dev.controlgastos.web.validator.ChangePasswordValidator;

@RequestMapping("/changepassword")
@Controller
public class ChangePasswordController {

	@Autowired
	private ChangePasswordValidator changePasswordValidator;
	@Autowired
	private PrincipalService principalService;
	
	@RequestMapping(produces = "text/html")
    public String changePasswordForm(HttpServletRequest request, Model uiModel) {
		changePasswordEditForm(uiModel);
        return "changepassword/changePassword";
    }

	private void changePasswordEditForm(Model uiModel) {
		Principal user = principalService.getPrincipalLogin();
		PrincipalChangePassword principalChangePassword = new PrincipalChangePassword(user);
        uiModel.addAttribute("principalChangePassword", principalChangePassword);
	}
	
	@RequestMapping(produces = "text/html",method = RequestMethod.POST)
    public String changePassword(@Valid PrincipalChangePassword principal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		changePasswordValidator.validate(principal, bindingResult);
		 if (bindingResult.hasErrors()) {
			 	changePasswordEditForm(uiModel);
			 	uiModel.addAllAttributes(bindingResult.getModel());
			 	return "changepassword/changePassword";
	        }
	        uiModel.asMap().clear();
	        principalService.changePassword(principal);
	        return "changepassword/changePasswordSuccessful";
    }
}
