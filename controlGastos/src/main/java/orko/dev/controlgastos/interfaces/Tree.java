package orko.dev.controlgastos.interfaces;

import java.io.Serializable;


public interface Tree<M extends Comparable & Serializable> {
	
	public Tree<M> getFather();
	public boolean isRoot();
	public M getComparator();

}
