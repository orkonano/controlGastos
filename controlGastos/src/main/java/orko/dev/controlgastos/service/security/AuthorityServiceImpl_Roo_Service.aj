// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.service.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orko.dev.controlgastos.model.security.Authority;
import orko.dev.controlgastos.repository.security.AuthorityRepository;
import orko.dev.controlgastos.service.security.AuthorityServiceImpl;

privileged aspect AuthorityServiceImpl_Roo_Service {
    
    declare @type: AuthorityServiceImpl: @Service;
    
    declare @type: AuthorityServiceImpl: @Transactional;
    
    @Autowired
    AuthorityRepository AuthorityServiceImpl.authorityRepository;
    
    public long AuthorityServiceImpl.countAllAuthoritys() {
        return authorityRepository.count();
    }
    
    public void AuthorityServiceImpl.deleteAuthority(Authority authority) {
        authorityRepository.delete(authority);
    }
    
    public Authority AuthorityServiceImpl.findAuthority(Long id) {
        return authorityRepository.findOne(id);
    }
    
    public List<Authority> AuthorityServiceImpl.findAllAuthoritys() {
        return authorityRepository.findAll();
    }
    
    public List<Authority> AuthorityServiceImpl.findAuthorityEntries(int firstResult, int maxResults) {
        return authorityRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void AuthorityServiceImpl.saveAuthority(Authority authority) {
        authorityRepository.save(authority);
    }
    
    public Authority AuthorityServiceImpl.updateAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }
    
}