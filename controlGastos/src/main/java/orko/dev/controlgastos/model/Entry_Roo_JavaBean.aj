// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package orko.dev.controlgastos.model;

import orko.dev.controlgastos.model.Entry;
import orko.dev.controlgastos.model.EntryType;
import orko.dev.controlgastos.model.security.Principal;

privileged aspect Entry_Roo_JavaBean {
    
    public EntryType Entry.getEntryType() {
        return this.entryType;
    }
    
    public void Entry.setEntryType(EntryType entryType) {
        this.entryType = entryType;
    }
    
    public String Entry.getDescription() {
        return this.description;
    }
    
    public void Entry.setDescription(String description) {
        this.description = description;
    }
    
    public Boolean Entry.getAttributable() {
        return this.attributable;
    }
    
    public void Entry.setAttributable(Boolean attributable) {
        this.attributable = attributable;
    }
    
    public Integer Entry.getPriority() {
        return this.priority;
    }
    
    public void Entry.setPriority(Integer priority) {
        this.priority = priority;
    }
    
    public Entry Entry.getFather() {
        return this.father;
    }
    
    public void Entry.setFather(Entry father) {
        this.father = father;
    }
    
    public Principal Entry.getUser() {
        return this.user;
    }
    
    public void Entry.setUser(Principal user) {
        this.user = user;
    }
    
}