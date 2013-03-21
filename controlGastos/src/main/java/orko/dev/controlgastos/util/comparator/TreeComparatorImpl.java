package orko.dev.controlgastos.util.comparator;

import java.io.Serializable;
import java.util.Comparator;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orko.dev.controlgastos.interfaces.Tree;
import orko.dev.controlgastos.util.tree.TreeUtils;

@Component
public class TreeComparatorImpl<M extends Comparable & Serializable> implements Comparator<Tree<M>> {

	@Autowired
	private TreeUtils treeUtils;
	
	@Override
	public int compare(Tree<M> o1, Tree<M> o2) {
		int ret = 0;
		int depth1 = treeUtils.getDepth(o1);
		int depth2 = treeUtils.getDepth(o2);
		int maxDepth = Math.max(depth1, depth2);
		int minDepth = Math.min(depth1, depth2);
		int indexDepth = 0;
		Tree<M> nodeFather1 = null;
		Tree<M> nodeFather2 = null;
		for( ;indexDepth<= minDepth;indexDepth++){
			nodeFather1 = treeUtils.getFatherNode(o1, indexDepth);
			nodeFather2 = treeUtils.getFatherNode(o2, indexDepth);
			ret = nodeFather1.getComparator().compareTo(nodeFather2.getComparator());
			if (ret != 0){
				break;
			}
		}
		if (ret == 0 && indexDepth>= minDepth && minDepth != maxDepth){
			ret = depth1 > depth2 ? 1 :-1;
		}
		return ret;
	}}
