package orko.dev.controlgastos.web.security;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import orko.dev.controlgastos.model.security.AuthorityPrincipalAssignment;

@RequestMapping("/security/assignments")
@Controller
@RooWebScaffold(path = "security/assignments", formBackingObject = AuthorityPrincipalAssignment.class)
public class RoleMappingContoller {
}
