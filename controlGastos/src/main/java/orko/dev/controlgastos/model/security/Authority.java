package orko.dev.controlgastos.model.security;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity(table = "security_authorities")
public class Authority {

    @NotNull
    @Size(min = 8, max = 10)
    @Pattern(regexp = "^ROLE_[A-Z]*")
    private String authority;
}
