package orko.dev.controlgastos.util.comparator.interfaces;

import java.util.Comparator;

import orko.dev.controlgastos.model.BudgetEntry;

public interface BudgetEntryComparator extends Comparator<BudgetEntry> {
	
	public int compare(BudgetEntry o1, BudgetEntry o2);

}