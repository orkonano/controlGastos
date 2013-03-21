// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import orko.dev.controlgastos.model.EconomicFact;
import orko.dev.controlgastos.repository.EconomicFactRepository;

privileged aspect EconomicFactRepository_Roo_Jpa_Repository {
    
    declare parents: EconomicFactRepository extends JpaRepository<EconomicFact, Long>;
    
    declare parents: EconomicFactRepository extends JpaSpecificationExecutor<EconomicFact>;
    
    declare @type: EconomicFactRepository: @Repository;
    
}
