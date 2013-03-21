package orko.dev.controlgastos.util.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import orko.dev.controlgastos.model.Entry;
import orko.dev.controlgastos.model.EntryDataOnDemand;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
public class TreeUtilsTest {
	
	@Autowired
	private TreeUtils treeUtils;
	private Entry root;
	private Entry node11;
	private Entry node12;
	private Entry node111;
	private Entry node112;
	
	@Before
	public void init(){
		root = new EntryDataOnDemand().getNewTransientEntry(1);
		root.setPriority(1);
		root.setFather(null);
		
		node11 = new EntryDataOnDemand().getNewTransientEntry(2);
		node12 = new EntryDataOnDemand().getNewTransientEntry(3);
		node11.setFather(root);
		node12.setFather(root);
		
		node111 = new EntryDataOnDemand().getNewTransientEntry(4);
		node112 = new EntryDataOnDemand().getNewTransientEntry(5);
		node111.setFather(node11);
		node112.setFather(node11);
		
	}

	@Test
	public void testGetRoot() {
		
		Assert.assertTrue(root.getComparator().compareTo(treeUtils.getRoot(root).getComparator()) == 0);
		
		Assert.assertTrue(root.getComparator().compareTo(treeUtils.getRoot(node11).getComparator()) == 0);
		Assert.assertTrue(root.getComparator().compareTo(treeUtils.getRoot(node12).getComparator()) == 0);
		
		Assert.assertTrue(root.getComparator().compareTo(treeUtils.getRoot(node111).getComparator()) == 0);
		Assert.assertTrue(root.getComparator().compareTo(treeUtils.getRoot(node112).getComparator()) == 0);
	}
	
	@Test
	public void testGetDepth() {
		Assert.assertEquals(treeUtils.getDepth(root),0);

		Assert.assertEquals(treeUtils.getDepth(node11),1);
		Assert.assertEquals(treeUtils.getDepth(node12),1);

		Assert.assertEquals(treeUtils.getDepth(node111),2);
		Assert.assertEquals(treeUtils.getDepth(node112),2);
	}
	
	@Test(expected=InvalidDepthTreeException.class)
	public void testGetDepthException() {
		treeUtils.getFatherNode(root,1);
	}
	
	@Test
	public void testGetFatherNode() {
		Assert.assertTrue(treeUtils.getFatherNode(root, 0).getComparator().compareTo(root.getComparator())==0);
		
		Assert.assertTrue(treeUtils.getFatherNode(node11, 0).getComparator().compareTo(root.getComparator())==0);
		Assert.assertTrue(treeUtils.getFatherNode(node11, 1).getComparator().compareTo(node11.getComparator())==0);

		Assert.assertTrue(treeUtils.getFatherNode(node12, 0).getComparator().compareTo(root.getComparator())==0);
		Assert.assertTrue(treeUtils.getFatherNode(node12, 1).getComparator().compareTo(node12.getComparator())==0);
		
		Assert.assertTrue(treeUtils.getFatherNode(node111, 0).getComparator().compareTo(root.getComparator())==0);
		Assert.assertTrue(treeUtils.getFatherNode(node111, 1).getComparator().compareTo(node11.getComparator())==0);
		Assert.assertTrue(treeUtils.getFatherNode(node111, 2).getComparator().compareTo(node111.getComparator())==0);
		
		Assert.assertTrue(treeUtils.getFatherNode(node112, 0).getComparator().compareTo(root.getComparator())==0);
		Assert.assertTrue(treeUtils.getFatherNode(node112, 1).getComparator().compareTo(node11.getComparator())==0);
		Assert.assertTrue(treeUtils.getFatherNode(node112, 2).getComparator().compareTo(node112.getComparator())==0);
	
	}

}
