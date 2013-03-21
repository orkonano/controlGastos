package orko.dev.controlgastos.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import orko.dev.controlgastos.model.security.form.PrincipalChangePassword;
import orko.dev.controlgastos.service.security.PrincipalService;

@Component
public class ChangePasswordValidator implements Validator {
	
	@Autowired
	private  PrincipalService principalService;

	@Override
	public boolean supports(Class<?> arg0) {
		return PrincipalChangePassword.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		PrincipalChangePassword principalChangePassword = (PrincipalChangePassword) arg0;
		if (!principalService.isValidActualPassword(principalChangePassword.getOldPassword())){
			arg1.rejectValue("oldPassword", "security_changePassword.invalidpassword");
		}
	}

}
