package orko.dev.controlgastos.model.security;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity(table = "security_principals")
public class Principal {

    @NotNull
    @Column(unique = true)
    @Size(min = 3, max = 50)
    private String username;

    @Size(min = 3)
    private String password;

    private Boolean enabled;

    @Size(min = 3, max = 50)
    @NotNull
    private String name;

    @Size(min = 3, max = 50)
    @NotNull
    private String lastName;

    @Size(min = 3, max = 50)
    @Email
    @NotNull
    private String email;
}
