package orko.dev.controlgastos.model;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import orko.dev.controlgastos.interfaces.Tree;
import orko.dev.controlgastos.model.interfaces.Owneable;
import orko.dev.controlgastos.model.security.Principal;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Entry implements Tree<Integer>, Owneable<Principal>{

    @Enumerated(EnumType.STRING)
    private EntryType entryType;

    @NotNull
    @Size(max = 200)
    private String description;

    private Boolean attributable;

    @NotNull
    @Min(1L)
    private Integer priority;

    @ManyToOne(optional=true)
    private Entry father;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
	private Principal user;
    
    @Transient
    public String getFullDescription(){
    	return new StringBuilder()
    			.append(this.getFather()!=null ? this.father.getDescription():"")
    			.append(this.getFather()!=null ? " - ":"").append(this.getDescription()).toString();
    }

	@Override
	@Transient
	public Integer getComparator() {
		return this.priority;
	}

	@Override
	@Transient
	public boolean isRoot() {
		return this.father != null ? false : true;
	}

	public boolean isNegativeResult() {
		return this.getEntryType().equals(EntryType.LOSS) || this.getEntryType().equals(EntryType.SAVING);
	}
}
