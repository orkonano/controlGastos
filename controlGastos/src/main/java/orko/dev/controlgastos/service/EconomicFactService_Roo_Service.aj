// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.service;

import java.util.List;
import orko.dev.controlgastos.model.EconomicFact;
import orko.dev.controlgastos.service.EconomicFactService;

privileged aspect EconomicFactService_Roo_Service {
    
    public abstract long EconomicFactService.countAllEconomicFacts();    
    public abstract void EconomicFactService.deleteEconomicFact(EconomicFact economicFact);    
    public abstract EconomicFact EconomicFactService.findEconomicFact(Long id);    
    public abstract List<EconomicFact> EconomicFactService.findAllEconomicFacts();    
    public abstract List<EconomicFact> EconomicFactService.findEconomicFactEntries(int firstResult, int maxResults);    
    public abstract void EconomicFactService.saveEconomicFact(EconomicFact economicFact);    
    public abstract EconomicFact EconomicFactService.updateEconomicFact(EconomicFact economicFact);    
}
