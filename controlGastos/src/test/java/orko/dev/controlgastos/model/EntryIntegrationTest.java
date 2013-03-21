package orko.dev.controlgastos.model;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.test.RooIntegrationTest;

import orko.dev.controlgastos.service.EntryService;

@RooIntegrationTest(entity = Entry.class)
public class EntryIntegrationTest extends CommonTestClass {

	@Autowired
	private EntryDataOnDemand dod;

	@Autowired
	EntryService entryService;
	

	@Test
	public void testMarkerMethod() {
	}

	@Test
	public void testDeleteEntry() {
		Entry obj = dod.getSpecificEntry(10);
		Assert.assertNotNull( "Data on demand for 'Entry' failed to initialize correctly", obj);
		Long id = obj.getId();
		Assert.assertNotNull( "Data on demand for 'Entry' failed to provide an identifier", id);
		obj = entryService.findEntry(id);
		entryService.deleteEntry(obj);
		entryRepository.flush();
		Assert.assertNull("Failed to remove 'Entry' with identifier '" + id + "'", entryService.findEntry(id));
	}
}
