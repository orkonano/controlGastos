// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.model.security;

import orko.dev.controlgastos.model.security.Authority;
import orko.dev.controlgastos.model.security.AuthorityPrincipalAssignment;
import orko.dev.controlgastos.model.security.Principal;

privileged aspect AuthorityPrincipalAssignment_Roo_JavaBean {
    
    public Principal AuthorityPrincipalAssignment.getUsername() {
        return this.username;
    }
    
    public void AuthorityPrincipalAssignment.setUsername(Principal username) {
        this.username = username;
    }
    
    public Authority AuthorityPrincipalAssignment.getRoleId() {
        return this.roleId;
    }
    
    public void AuthorityPrincipalAssignment.setRoleId(Authority roleId) {
        this.roleId = roleId;
    }
    
}