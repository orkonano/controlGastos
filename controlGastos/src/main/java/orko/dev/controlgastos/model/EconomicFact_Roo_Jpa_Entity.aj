// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import orko.dev.controlgastos.model.EconomicFact;

privileged aspect EconomicFact_Roo_Jpa_Entity {
    
    declare @type: EconomicFact: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long EconomicFact.id;
    
    @Version
    @Column(name = "version")
    private Integer EconomicFact.version;
    
    public Long EconomicFact.getId() {
        return this.id;
    }
    
    public void EconomicFact.setId(Long id) {
        this.id = id;
    }
    
    public Integer EconomicFact.getVersion() {
        return this.version;
    }
    
    public void EconomicFact.setVersion(Integer version) {
        this.version = version;
    }
    
}