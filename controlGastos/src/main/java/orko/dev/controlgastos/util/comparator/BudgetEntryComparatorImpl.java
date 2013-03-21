package orko.dev.controlgastos.util.comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orko.dev.controlgastos.model.BudgetEntry;
import orko.dev.controlgastos.util.comparator.interfaces.BudgetEntryComparator;

@Component
public class BudgetEntryComparatorImpl implements BudgetEntryComparator {
	
	@Autowired
	private EntryComparatorImpl entryComparator;

	@Override
	public int compare(BudgetEntry o1, BudgetEntry o2) {
		return entryComparator.compare(o1.getEntry(), o2.getEntry());
	}

}
