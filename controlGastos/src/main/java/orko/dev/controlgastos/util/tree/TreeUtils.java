package orko.dev.controlgastos.util.tree;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import orko.dev.controlgastos.interfaces.Tree;

@Component
public class TreeUtils {
	
	public <M extends Comparable & Serializable> Tree<M> getRoot(Tree<M> tree){
		return !tree.isRoot() ?getRoot(tree.getFather()):tree;
	}
	
	public <M extends Comparable & Serializable> int getDepth(Tree<M> tree){
		return getDepth(tree, 0);
	}
	
	public <M extends Comparable & Serializable> Tree<M> getFatherNode(Tree<M> tree,int depth){
		if (depth == 0){
			return getRoot(tree);
		}else{
			if (getDepth(tree) == depth){
				return tree;
			}else{
				if (getDepth(tree) < depth){
					throw new InvalidDepthTreeException("La profundidad es mayor a la buscada");
				}else{
					return getFatherNode(tree.getFather(), depth);
				}
			}
		}
	}
	
	
	private <M extends Comparable & Serializable> int getDepth(Tree<M> tree,int depth){
		if (tree.isRoot()){
			return depth;
		}else{
			return getDepth(tree.getFather(), ++depth);
		}
	}

}
