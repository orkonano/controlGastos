// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.service.security;

import java.util.List;
import orko.dev.controlgastos.model.security.Principal;
import orko.dev.controlgastos.service.security.PrincipalService;

privileged aspect PrincipalService_Roo_Service {
    
    public abstract long PrincipalService.countAllPrincipals();    
    public abstract void PrincipalService.deletePrincipal(Principal principal);    
    public abstract Principal PrincipalService.findPrincipal(Long id);    
    public abstract List<Principal> PrincipalService.findAllPrincipals();    
    public abstract List<Principal> PrincipalService.findPrincipalEntries(int firstResult, int maxResults);    
    public abstract void PrincipalService.savePrincipal(Principal principal);    
    public abstract Principal PrincipalService.updatePrincipal(Principal principal);    
}