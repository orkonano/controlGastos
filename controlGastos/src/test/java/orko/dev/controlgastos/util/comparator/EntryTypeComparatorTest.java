package orko.dev.controlgastos.util.comparator;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import orko.dev.controlgastos.model.EntryType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
public class EntryTypeComparatorTest {
	
	@Autowired
	private EntryTypeComparatorImpl entryTypeComparator;

	@Test
	public void testCompare() {
		Assert.assertTrue(entryTypeComparator.compare(EntryType.OPEN_BALANCE, EntryType.OPEN_BALANCE) == 0);
		Assert.assertTrue(entryTypeComparator.compare(EntryType.OPEN_BALANCE, EntryType.INGRESS) == -1);
		Assert.assertTrue(entryTypeComparator.compare(EntryType.OPEN_BALANCE, EntryType.SAVING) == -1);
		Assert.assertTrue(entryTypeComparator.compare(EntryType.OPEN_BALANCE, EntryType.LOSS) == -1);
		
		Assert.assertTrue(entryTypeComparator.compare(EntryType.INGRESS, EntryType.OPEN_BALANCE) == 1);
		Assert.assertTrue(entryTypeComparator.compare(EntryType.INGRESS, EntryType.INGRESS) == 0);
		Assert.assertTrue(entryTypeComparator.compare(EntryType.INGRESS, EntryType.SAVING) == -1);
		Assert.assertTrue(entryTypeComparator.compare(EntryType.INGRESS, EntryType.LOSS) == -1);
		
		Assert.assertTrue(entryTypeComparator.compare(EntryType.SAVING, EntryType.OPEN_BALANCE) == 1);
		Assert.assertTrue(entryTypeComparator.compare(EntryType.SAVING, EntryType.INGRESS) == 1);
		Assert.assertTrue(entryTypeComparator.compare(EntryType.SAVING, EntryType.SAVING) == 0);
		Assert.assertTrue(entryTypeComparator.compare(EntryType.SAVING, EntryType.LOSS) == -1);
	
		Assert.assertTrue(entryTypeComparator.compare(EntryType.LOSS, EntryType.OPEN_BALANCE) == 1);
		Assert.assertTrue(entryTypeComparator.compare(EntryType.LOSS, EntryType.INGRESS) == 1);
		Assert.assertTrue(entryTypeComparator.compare(EntryType.LOSS, EntryType.SAVING) == 1);
		Assert.assertTrue(entryTypeComparator.compare(EntryType.LOSS, EntryType.LOSS) == 0);
	}

}
