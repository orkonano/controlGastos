package orko.dev.controlgastos.model.security.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import orko.dev.controlgastos.model.security.Principal;
import orko.dev.controlgastos.util.security.PrincipalHandler;

@Configurable
public class PrincipalChangePassword {

	@Autowired
	private StandardPasswordEncoder encoder;
	@Autowired
	private PrincipalHandler principalHandler;

	private Principal user;
	@NotEmpty
	@NotNull
	private String oldPassword;
	@NotEmpty
	@NotNull
	private String newPassword;
	@NotEmpty
	@NotNull
	private String newPasswordRepeat;

	public PrincipalChangePassword(Principal user) {
		this.user = user;
	}

	public PrincipalChangePassword() {
		super();
	}

	public Principal getUser() {
		return user;
	}

	public void setUser(Principal user) {
		this.user = user;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordRepeat() {
		return newPasswordRepeat;
	}

	public void setNewPasswordRepeat(String newPasswordRepeat) {
		this.newPasswordRepeat = newPasswordRepeat;
	}

	
	@AssertTrue
	public boolean isValidNewPassword() {
		return this.newPassword.equals(this.newPasswordRepeat);
	}
	
	public String getId(){
		return this.getUser().getId().toString();
	}
	
	public void setId(String id){
		Principal user = new Principal();
		user.setId(Long.valueOf(id));
		this.setUser(user);
	}

}
