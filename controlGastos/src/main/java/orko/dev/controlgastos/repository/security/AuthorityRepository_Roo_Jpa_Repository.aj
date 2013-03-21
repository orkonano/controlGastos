// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import orko.dev.controlgastos.model.security.Authority;
import orko.dev.controlgastos.repository.security.AuthorityRepository;

privileged aspect AuthorityRepository_Roo_Jpa_Repository {
    
    declare parents: AuthorityRepository extends JpaRepository<Authority, Long>;
    
    declare parents: AuthorityRepository extends JpaSpecificationExecutor<Authority>;
    
    declare @type: AuthorityRepository: @Repository;
    
}
