package orko.dev.controlgastos.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import orko.dev.controlgastos.model.security.Principal;
import orko.dev.controlgastos.structs.MasiveMail;

@RequestMapping("/security/users")
@Controller
@RooWebScaffold(path = "security/users", formBackingObject = Principal.class,update=false)
public class UserContoller {
	
	@RequestMapping(value = "/sendMasiveMail",method = RequestMethod.POST, produces = "text/html")
    public String sendMasiveMail(@Valid MasiveMail mail, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
        	uiModel.addAttribute("masiveMail", mail);
            return "security/users/sendMasiveMail";
        }
        uiModel.asMap().clear();
        principalService.enviarMailAllUser(mail);
        return "redirect:/";
    }
	
}
