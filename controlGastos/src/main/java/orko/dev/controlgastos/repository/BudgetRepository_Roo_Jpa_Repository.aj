// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import orko.dev.controlgastos.model.Budget;
import orko.dev.controlgastos.repository.BudgetRepository;

privileged aspect BudgetRepository_Roo_Jpa_Repository {
    
    declare parents: BudgetRepository extends JpaRepository<Budget, Long>;
    
    declare parents: BudgetRepository extends JpaSpecificationExecutor<Budget>;
    
    declare @type: BudgetRepository: @Repository;
    
}
