package orko.dev.controlgastos.util.comparator;

import junit.framework.Assert;

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
public class TreeComparatorTest {
	
	@Autowired
	private TreeComparatorImpl<Integer> treeComparator;
	private Entry root1;
	private Entry nodeR1_11;
	private Entry nodeR1_12;
	private Entry nodeR1_111;
	private Entry nodeR1_112;
	
	private Entry root2;
	private Entry nodeR2_11;
	private Entry nodeR2_12;
	private Entry nodeR2_111;
	private Entry nodeR2_112;
	
	@Before
	public void init(){
		generateTree1();
		generateTree2();
	}

	private void generateTree1() {
		root1 = new EntryDataOnDemand().getNewTransientEntry(1);
		root1.setPriority(1);
		root1.setFather(null);
		
		nodeR1_11 = new EntryDataOnDemand().getNewTransientEntry(2);
		nodeR1_12 = new EntryDataOnDemand().getNewTransientEntry(3);
		nodeR1_11.setFather(root1);
		nodeR1_11.setPriority(1);
		nodeR1_12.setFather(root1);
		nodeR1_12.setPriority(2);
		
		
		nodeR1_111 = new EntryDataOnDemand().getNewTransientEntry(4);
		nodeR1_112 = new EntryDataOnDemand().getNewTransientEntry(5);
		nodeR1_111.setFather(nodeR1_11);
		nodeR1_111.setPriority(1);
		nodeR1_112.setFather(nodeR1_11);
		nodeR1_112.setPriority(2);
	}
	
	private void generateTree2() {
		root2 = new EntryDataOnDemand().getNewTransientEntry(6);
		root2.setPriority(2);
		root2.setFather(null);
		
		nodeR2_11 = new EntryDataOnDemand().getNewTransientEntry(7);
		nodeR2_12 = new EntryDataOnDemand().getNewTransientEntry(8);
		nodeR2_11.setFather(root2);
		nodeR2_11.setPriority(1);
		nodeR2_12.setFather(root2);
		nodeR2_12.setPriority(2);
		
		
		nodeR2_111 = new EntryDataOnDemand().getNewTransientEntry(9);
		nodeR2_112 = new EntryDataOnDemand().getNewTransientEntry(10);
		nodeR2_111.setFather(nodeR2_11);
		nodeR2_111.setPriority(1);
		nodeR2_112.setFather(nodeR2_11);
		nodeR2_112.setPriority(2);
	}

	@Test
	public void testComparatorRoot() {
		Assert.assertTrue(treeComparator.compare(root1, root1) == 0);
		Assert.assertTrue(treeComparator.compare(root1, root2) == -1);
		Assert.assertTrue(treeComparator.compare(root2, root2) == 0);
		Assert.assertTrue(treeComparator.compare(root2, root1) == 1);
	}
	
	@Test
	public void testComparatorLevel1() {
		Assert.assertTrue(treeComparator.compare(nodeR1_11, nodeR1_11) == 0);
		Assert.assertTrue(treeComparator.compare(nodeR1_11, root1) == 1);
		Assert.assertTrue(treeComparator.compare(nodeR1_11, root2) == -1);
		Assert.assertTrue(treeComparator.compare(nodeR1_11, nodeR2_11) == -1);
		Assert.assertTrue(treeComparator.compare(nodeR1_11, nodeR1_111) == -1);
		Assert.assertTrue(treeComparator.compare(nodeR1_11, nodeR1_112) == -1);
		Assert.assertTrue(treeComparator.compare(nodeR1_11, nodeR2_111) == -1);
		Assert.assertTrue(treeComparator.compare(nodeR1_11, nodeR2_112) == -1);
		
		Assert.assertTrue(treeComparator.compare(nodeR2_11, nodeR2_11) == 0);
		Assert.assertTrue(treeComparator.compare(nodeR2_11, root1) == 1);
		Assert.assertTrue(treeComparator.compare(nodeR2_11, root2) == 1);
		Assert.assertTrue(treeComparator.compare(nodeR2_11, nodeR1_11) == 1);
		Assert.assertTrue(treeComparator.compare(nodeR2_11, nodeR1_111) == 1);
		Assert.assertTrue(treeComparator.compare(nodeR2_11, nodeR1_112) == 1);
		Assert.assertTrue(treeComparator.compare(nodeR2_11, nodeR2_111) == -1);
		Assert.assertTrue(treeComparator.compare(nodeR2_11, nodeR2_112) == -1);
		
	}

}