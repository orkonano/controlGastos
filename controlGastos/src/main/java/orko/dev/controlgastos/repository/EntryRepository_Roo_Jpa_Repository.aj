// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import orko.dev.controlgastos.model.Entry;
import orko.dev.controlgastos.repository.EntryRepository;

privileged aspect EntryRepository_Roo_Jpa_Repository {
    
    declare parents: EntryRepository extends JpaRepository<Entry, Long>;
    
    declare parents: EntryRepository extends JpaSpecificationExecutor<Entry>;
    
    declare @type: EntryRepository: @Repository;
    
}
