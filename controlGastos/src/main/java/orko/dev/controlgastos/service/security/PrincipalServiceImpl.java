package orko.dev.controlgastos.service.security;

import java.security.SecureRandom;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import orko.dev.controlgastos.email.NotificationService;
import orko.dev.controlgastos.model.security.Principal;
import orko.dev.controlgastos.model.security.form.PrincipalChangePassword;
import orko.dev.controlgastos.structs.MasiveMail;
import orko.dev.controlgastos.util.security.PrincipalHandler;



public class PrincipalServiceImpl implements PrincipalService {
	
	
	@Autowired
	private PrincipalHandler principalHandler;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private AuthorityPrincipalAssignmentService assignmentService;
	

	@Override
	@Transactional(readOnly=true)
	public Principal getPrincipalLogin() {
		return this.principalRepository.getPrincipalByUsername(principalHandler.getUser().getUsername());
	}

	@Transactional
	public void savePrincipal(Principal principal) {
		String password = RandomStringUtils.random(8, 0, 0, true, true, null, new SecureRandom());
		principal.setPassword(password);
        principalRepository.save(principal);
        assignmentService.saveDefaultRole(principal);
        String message = this.getMailLoginMessage(principal,password);
        this.notificationService.sendMessage(principal.getEmail(), message);
    }
	
	private String getMailLoginMessage(Principal principal, String password) {
		StringBuilder sb = new StringBuilder();
		sb.append("Bienvenido a Control de Gastos");
		sb.append("\n\n\n");
		sb.append("Usted ha sido invitado para usar y probar el sistema que le facilita llevar un control de sus gastos en forma online y segura.");
		sb.append("\n\n\n");
		sb.append("\n\n\n");
		sb.append(" Para acceder usted debe dirigirse a la siguiente dirección http://controlgastos.cloudfoundry.com/login \n");
		sb.append("usuario: "+principal.getUsername()).append(" \n");
		sb.append("contraseña: "+password).append(" \n");
		sb.append("\n\n\n");
		sb.append("\n\n\n");
		sb.append("Esperamos que le sea de utilidad.").append("\n");
		sb.append("Ante cualquier duda o soporte escriba a marianoekfuri@gmail.com.");
		return sb.toString();
	}

	@Override
	@Transactional
	public void changePassword(PrincipalChangePassword principal) {
		principalRepository.changePassword(this.getPrincipalLogin(), principal.getNewPassword());
	}

	@Override
	public boolean isValidActualPassword(String oldPassword) {
		return this.principalRepository.getPasswordEncoder().matches(oldPassword, this.getPrincipalLogin().getPassword());
	}
	
	@Override
	public void enviarMailAllUser(MasiveMail masiveMail){
		List<Principal> users = this.principalRepository.findAll();
		for (Principal principal : users) {
			notificationService.sendMessage(principal.getEmail(), masiveMail.getBody(), masiveMail.getSubject());
		}
	}
}
