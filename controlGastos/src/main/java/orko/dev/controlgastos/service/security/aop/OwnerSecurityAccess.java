package orko.dev.controlgastos.service.security.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import orko.dev.controlgastos.model.interfaces.Owneable;
import orko.dev.controlgastos.model.security.Principal;
import orko.dev.controlgastos.service.security.PrincipalService;


public class OwnerSecurityAccess {
	
	@Autowired
	private PrincipalService principalService;

	public void checkDomainOwner(Owneable<?> objectToCheck){
		if (objectToCheck != null){
			Principal ownerObject = (Principal)objectToCheck.getUser();
			Principal userLogin = (Principal) this.principalService.getPrincipalLogin();
			if (!ownerObject.equals(userLogin)){
				throw new AccessDeniedException("Usted no tiene acceso.");
			}
		}
	}
	
}
