package orko.dev.controlgastos.model.security;

import javax.persistence.ManyToOne;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity(table = "security_role_assignments")
public class AuthorityPrincipalAssignment {

    @ManyToOne
    private Principal username;

    @ManyToOne
    private Authority roleId;
}
