// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import orko.dev.controlgastos.model.security.Principal;
import orko.dev.controlgastos.repository.security.PrincipalRepository;

privileged aspect PrincipalRepository_Roo_Jpa_Repository {
    
    declare parents: PrincipalRepository extends JpaRepository<Principal, Long>;
    
    declare parents: PrincipalRepository extends JpaSpecificationExecutor<Principal>;
    
    declare @type: PrincipalRepository: @Repository;
    
}