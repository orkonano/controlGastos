// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import orko.dev.controlgastos.model.BudgetEntry;

privileged aspect BudgetEntry_Roo_Jpa_Entity {
    
    declare @type: BudgetEntry: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long BudgetEntry.id;
    
    @Version
    @Column(name = "version")
    private Integer BudgetEntry.version;
    
    public Long BudgetEntry.getId() {
        return this.id;
    }
    
    public void BudgetEntry.setId(Long id) {
        this.id = id;
    }
    
    public Integer BudgetEntry.getVersion() {
        return this.version;
    }
    
    public void BudgetEntry.setVersion(Integer version) {
        this.version = version;
    }
    
}
